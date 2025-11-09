// Central place to collect lightweight runtime metrics for the overlay.
// Designed to be GUI-only and cheap to update.
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import Enums.Channel;

public class Diagnostics 
{
    private static final Diagnostics INSTANCE = new Diagnostics();
    public static Diagnostics get() { return INSTANCE; }

    // Track last update wall-clock time (ms) per channel.
    private final Map<Channel, Long> lastUpdateMs = new EnumMap<>(Channel.class);

    // Simple queue depth counters per channel (incremented when we detect a pending delivery).
    private final Map<Channel, AtomicInteger> queueDepth = new EnumMap<>(Channel.class);

    // Exponential moving average (ms) for notify -> render latency.
    private final Map<Channel, Double> latencyEmaMs = new EnumMap<>(Channel.class);

    // Dropped frame counts per channel.
    private final Map<Channel, Integer> droppedFrames = new EnumMap<>(Channel.class);

    // Small registry showing whether a subject is in PUSH or PULL mode.
    private final Map<String, String> modeBySubject = new java.util.concurrent.ConcurrentHashMap<>();

    // EMA parameter (reasonable smoothing).
    private final double EMA_ALPHA = 0.18;

    private Diagnostics() 
    {
        for (Channel c : Channel.values()) 
        {
            queueDepth.put(c, new AtomicInteger(0));
            latencyEmaMs.put(c, 0.0);
            droppedFrames.put(c, 0);
        }
    }

    // Called whenever a subject emits an event for the given channel.
    public void recordEvent(Channel ch) 
    {
        lastUpdateMs.put(ch, System.currentTimeMillis());
    }

    // Update latency EMA in milliseconds.
    public void recordLatency(Channel ch, long nanos) 
    {
        double ms = nanos / 1_000_000.0;
        latencyEmaMs.put(ch, latencyEmaMs.getOrDefault(ch, 0.0) * (1 - EMA_ALPHA) + ms * EMA_ALPHA);
    }

    // Queue counters (incremented when an observer is busy and we coalesce).
    public void incQueue(Channel ch) 
    { 
        queueDepth.get(ch).incrementAndGet(); 
    }
    public void decQueue(Channel ch) 
    {
        AtomicInteger v = queueDepth.get(ch);
        if (v.get() > 0) v.decrementAndGet();
    }

    public void dropFrame(Channel ch) { droppedFrames.put(ch, droppedFrames.getOrDefault(ch, 0) + 1); }

    public void setMode(String subjectName, String mode)
    { 
        modeBySubject.put(subjectName, mode); 
    }

    // Small HTML snippet suitable for a JLabel; keeps the overlay simple.
    public String summaryHtml() 
    {
        StringBuilder sb = new StringBuilder("<html><body style='font-family:monospace'>");
        for (Channel c : Channel.values()) 
        {
            Long t = lastUpdateMs.get(c);
            String age = (t == null) ? "never" : (System.currentTimeMillis() - t) + " ms";
            sb.append(String.format("<b>%s</b>: last=%s q=%d lat=%.2fms drop=%d<br>",
                    c,
                    age,
                    queueDepth.get(c).get(),
                    latencyEmaMs.getOrDefault(c, 0.0),
                    droppedFrames.getOrDefault(c, 0)
            ));
        }
        sb.append("<hr>");
        for (Map.Entry<String, String> e : modeBySubject.entrySet()) 
        {
            sb.append(e.getKey()).append(": ").append(e.getValue()).append("<br>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }
}