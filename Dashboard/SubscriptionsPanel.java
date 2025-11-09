import javax.swing.*;

import Enums.Channel;

import Subjects.*;
import Observers.*;

import java.awt.*;

public class SubscriptionsPanel extends JPanel 
{

    private final JCheckBox weatherCb = new JCheckBox("Weather", true);
    private final JToggleButton weatherMode = new JToggleButton("Push");

    public SubscriptionsPanel(WeatherSubject weatherSubject, WeatherPanel weatherPanel,
                                AirQualitySubject airSubject, AirQualityPanel airPanel,
                                AlertsSubject alertsSubject, AlertsPanel alertsPanel) 
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Subscriptions"));

        //Weather Subscription Area
        JPanel weather = new JPanel();
        weather.setLayout(new BoxLayout(weather, BoxLayout.Y_AXIS));        
        weather.setBorder(BorderFactory.createTitledBorder("Weather"));

        JCheckBox weatherActive = new JCheckBox("Active", true);
        JComboBox<String> weatherMode = new JComboBox<>(new String[]{"Push", "Pull"});
        weatherMode.setMaximumSize(new Dimension(80, 25));
        JPanel weatherTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weatherTop.add(weatherActive);
        weatherTop.add(new JLabel("Mode: "));
        weatherTop.add(weatherMode);
        weather.add(weatherTop);
        add(weather);


        //Air Quality Subscription Area
        JPanel aq = new JPanel();
        aq.setLayout(new BoxLayout(aq, BoxLayout.Y_AXIS));        
        aq.setBorder(BorderFactory.createTitledBorder("Air Quality"));

        JCheckBox aqActive = new JCheckBox("Active", true);
        JComboBox<String> aqMode = new JComboBox<>(new String[]{"Push", "Pull"});
        aqMode.setMaximumSize(new Dimension(80, 25));
        JPanel aqTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        aqTop.add(aqActive);
        aqTop.add(new JLabel("Mode: "));
        aqTop.add(aqMode);
        aq.add(aqTop);
        add(aq);


        //Alerts Subscription Area
        JPanel alerts = new JPanel();
        alerts.setLayout(new BoxLayout(alerts, BoxLayout.Y_AXIS));        
        alerts.setBorder(BorderFactory.createTitledBorder("Alerts"));

        JCheckBox alertsActive = new JCheckBox("Active", true);
        JComboBox<String> alertsMode = new JComboBox<>(new String[]{"Push", "Pull"});
        alertsMode.setMaximumSize(new Dimension(80, 25));
        JPanel alertsTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        alertsTop.add(alertsActive);
        alertsTop.add(new JLabel("Mode: "));
        alertsTop.add(alertsMode);
        alerts.add(alertsTop);
        add(alerts);

    }
}