import javax.swing.*;

import Enums.Channel;
import Subjects.*;
import Observers.*;

import java.awt.*;

public class DashboardFrame extends JFrame 
{
    private final WeatherSubject weatherSubject = new WeatherSubject();
    private final WeatherPanel weatherPanel = new WeatherPanel();

    //private final AlertSubject alertSubject = new AlertSubject(); // Not done
    private final AlertsPanel alertsPanel = new AlertsPanel(); // Not done

    private final MapPanel mapPanel = new MapPanel(); // Not done

    private final AirQualitySubject aqSubject = new AirQualitySubject(); // Not done
    
    private final AirQualityPanel aqPanel = new AirQualityPanel();  // Not done

    

    private final JLabel diagLabel = new JLabel();
    //private final SubscriptionsPanel subsPanel;
    private final TimeBar timeBar = new TimeBar();
    private boolean darkMode = false;

    public DashboardFrame() 
    {
        super("Observer-Only Smart Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());

        // Top bar
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel title = new JLabel("Observer-Only Smart Dashboard");
        title.setFont(title.getFont().deriveFont(16f));
        JToggleButton themeToggle = new JToggleButton("Dark");
        JToggleButton diagToggle = new JToggleButton("Diagnostics");

        themeToggle.addActionListener(e -> toggleTheme(themeToggle.isSelected()));
        diagToggle.addItemListener(e -> diagLabel.setVisible(diagToggle.isSelected()));

        topBar.add(title);
        topBar.add(themeToggle);
        topBar.add(diagToggle);
        add(topBar, BorderLayout.NORTH);

        // Left drawer (subscriptions)
        // subsPanel = new SubscriptionsPanel(weatherSubject, weatherPanel);
        // subsPanel.setPreferredSize(new Dimension(200, 100));
        // add(subsPanel, BorderLayout.WEST);

        // Center area with draggable absolute layout
        JLayeredPane center = new JLayeredPane();
        center.setLayout(null); // absolute positioning for draggable cards
        add(center, BorderLayout.CENTER);

        // Create cards
        DraggableCard weatherCard = new DraggableCard("Weather", weatherPanel);
        DraggableCard mapCard = new DraggableCard("Map", mapPanel);
        DraggableCard airCard = new DraggableCard("Air Quality", aqPanel);
        DraggableCard alertsCard = new DraggableCard("Alerts", alertsPanel);

        // initial bounds
        weatherCard.setBounds(20, 20, 320, 200);
        mapCard.setBounds(360, 20, 320, 300);
        airCard.setBounds(20, 240, 320, 180);
        alertsCard.setBounds(360, 340, 320, 160);

        center.add(weatherCard, JLayeredPane.DEFAULT_LAYER);
        center.add(mapCard, JLayeredPane.DEFAULT_LAYER);
        center.add(airCard, JLayeredPane.DEFAULT_LAYER);
        center.add(alertsCard, JLayeredPane.DEFAULT_LAYER);

        // Bottom time bar
        add(timeBar, BorderLayout.SOUTH);

        // Diagnostics label (overlay area at top-right)
        diagLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        diagLabel.setOpaque(true);
        diagLabel.setBackground(new Color(255,255,255,200));
        diagLabel.setBounds(getWidth()-260, 10, 240, 120);
        diagLabel.setVisible(false);
        // We'll simply add it to NORTH as a lightweight overlay alternative
        topBar.add(Box.createHorizontalStrut(20));
        topBar.add(diagLabel);

        // Register observer and diagnostics updater
        weatherSubject.register(weatherPanel, Channel.WEATHER);
        //alertSubject.register(alertsPanel, Channel.ALERTS); - Come back to this
        //aqSubject.register(aqPanel, Channel.AIR_QUALITY); - Come back to this

        new javax.swing.Timer(500, e -> diagLabel.setText(Diagnostics.get().summaryHtml())).start();

        // Wire the timebar to a simple tick that currently forwards to the subject tick
        // For now we call weatherSubject.notify(Channel.WEATHER) on tick so the UI-only time bar drives it
        timeBar.setTickListener(simMillis -> {
            // In a full implementation you'd use simMillis as the simulated time index.
            // Here we use it as a simple driver to produce updates:
            weatherSubject.notifyObservers(Channel.WEATHER);
        });
    }

    private void toggleTheme(boolean dark) 
    {
        darkMode = dark;
        Color bg = dark ? new Color(34,34,34) : UIManager.getColor("Panel.background");
        Color fg = dark ? Color.WHITE : UIManager.getColor("Label.foreground");
        getContentPane().setBackground(bg);
        // naive theme propagation:
        SwingUtilities.updateComponentTreeUI(this);
        // ensure diag text readable
        diagLabel.setForeground(fg);
    }
}