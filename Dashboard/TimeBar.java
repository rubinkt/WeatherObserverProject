package Dashboard;

import javax.swing.*;
import java.awt.*;

public class TimeBar extends JPanel 
{
    private final JButton playPause = new JButton("Play");
    private final JButton step = new JButton("Step");
    private final JComboBox<String> speed = new JComboBox<>(new String[]{"1×","5×","20×"});
    private final JSlider scrubber = new JSlider(0, 1000, 0); // rolling buffer index
    private final JLabel timeLabel = new JLabel("0");

    private final Timer simTimer;
    private boolean playing = false;
    private int simPosition = 0;
    private int speedMultiplier = 1;

    public interface TickListener { 
    void tick(long simMillis); }

    private TickListener listener;

    public TimeBar() 
    {
        setLayout(new BorderLayout());

        // Controls
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(playPause);
        controls.add(step);
        controls.add(speed);
        controls.add(timeLabel);
        add(controls, BorderLayout.WEST);
        add(scrubber, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Time Bar"));

        // Base tick every 100 ms
        simTimer = new Timer(100, e -> advanceTick());

        //Base action listeners
        playPause.addActionListener(e -> togglePlay());
        step.addActionListener(e -> advanceTick());
        speed.addActionListener(e -> {
            String s = (String)speed.getSelectedItem();
            if ("5×".equals(s)) speedMultiplier = 5;
            else if ("20×".equals(s)) speedMultiplier = 20;
            else speedMultiplier = 1;
        });

        // Scrubber listener
        scrubber.addChangeListener(e -> {
            if (!scrubber.getValueIsAdjusting()) 
            {
                simPosition = scrubber.getValue();
                timeLabel.setText(String.valueOf(simPosition));
                if (listener != null) listener.tick(simPosition); // Notify listener of manual scrub
            }
        });
    }

    private void togglePlay() 
    {
        playing = !playing;
        playPause.setText(playing ? "Pause" : "Play");
        if (playing) simTimer.start(); else simTimer.stop();
    }

    private void advanceTick() 
    {
        simPosition += speedMultiplier;
        if (simPosition > scrubber.getMaximum()) simPosition = 0;
        scrubber.setValue(simPosition);
        timeLabel.setText(String.valueOf(simPosition));
        if (listener != null) listener.tick(simPosition);
    }

    public void setTickListener(TickListener l) { this.listener = l; }
    public void stop() { simTimer.stop(); }
    public void start() { simTimer.start(); }
}
