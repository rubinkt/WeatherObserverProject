import javax.swing.*;

import Enums.Channel;
import Observers.WeatherPanel;
import Subjects.WeatherSubject;

import java.awt.*;

public class SubscriptionsPanel extends JPanel 
{
    private final JCheckBox weatherCb = new JCheckBox("Weather", true);
    private final JToggleButton weatherMode = new JToggleButton("Push");

    public SubscriptionsPanel(WeatherSubject weatherSubject, WeatherPanel weatherPanel) 
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Subscriptions Manager"));

        // Weather row
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weatherMode.setPreferredSize(new Dimension(70, 24));
        weatherMode.addItemListener(e -> {
            boolean push = weatherMode.isSelected();
            weatherMode.setText(push ? "Push" : "Pull");
            weatherSubject.setMode(push);
        });
        weatherMode.setSelected(true); // default Push

        weatherCb.addItemListener(e -> {
            if (weatherCb.isSelected()) weatherSubject.register(weatherPanel, Channel.WEATHER);
            else weatherSubject.unregister(weatherPanel, Channel.WEATHER);
        });

        row.add(weatherCb);
        row.add(weatherMode);
        add(row);

        // Add glue so panel shrinks nicely
        add(Box.createVerticalGlue());
    }
}