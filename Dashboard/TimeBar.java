import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimeBar extends JPanel {
    private final JButton playPause = new JButton("Play");
    private final JButton step = new JButton("Step");
    private final JComboBox<String> speed = new JComboBox<>(new String[]{"1×","5×","20×"});
    private final JSlider scrubber = new JSlider(0, 1000, 1000); // rolling buffer index
    private final Timer simTimer;
    private boolean playing = false;
    private int simPosition = 0;
    private int speedMultiplier = 1;

    public interface TickListener { void tick(long simMillis); }
    private TickListener listener;

    public TimeBar() {
        setLayout(new BorderLayout());
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(playPause);
        controls.add(step);
        controls.add(speed);
        add(controls, BorderLayout.WEST);
        add(scrubber, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Time Bar"));

        simTimer = new Timer(200, e -> advanceTick()); // base tick every 200 ms

        playPause.addActionListener(e -> togglePlay());
        step.addActionListener(e -> doStep());
        speed.addActionListener(e -> {
            String s = (String)speed.getSelectedItem();
            if ("5×".equals(s)) speedMultiplier = 5;
            else if ("20×".equals(s)) speedMultiplier = 20;
            else speedMultiplier = 1;
        });

        scrubber.addChangeListener(e -> {
            if (!scrubber.getValueIsAdjusting()) {
                simPosition = scrubber.getValue();
                // notify listener that we scrubbed to simPosition (optional)
                if (listener != null) listener.tick(simPosition);
            }
        });
    }

    private void togglePlay() {
        playing = !playing;
        playPause.setText(playing ? "Pause" : "Play");
        if (playing) simTimer.start(); else simTimer.stop();
    }

    private void doStep() {
        for (int i=0;i<speedMultiplier;i++) advanceTick();
    }

    private void advanceTick() {
        simPosition = (simPosition + 1) % (scrubber.getMaximum() + 1);
        scrubber.setValue(simPosition);
        if (listener != null) listener.tick(simPosition);
    }

    public void setTickListener(TickListener l) { this.listener = l; }
    public void stop() { simTimer.stop(); }
    public void start() { simTimer.start(); }
}