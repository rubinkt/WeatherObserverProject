package Dashboard;

import javax.swing.*;

import Enums.Channel;

import Subjects.*;
import Observers.*;

import java.awt.*;
import java.awt.event.ItemEvent;

public class SubscriptionsPanel extends JPanel
{

    public SubscriptionsPanel(WeatherSubject weatherSubject, WeatherPanel weatherPanel,
                                AirQualitySubject airSubject, AirQualityPanel airPanel,
                                AlertsSubject alertsSubject, AlertsPanel alertsPanel, MapPanel mapPanel) 
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

        //Tarnsit Subscription Area
        JPanel transit = new JPanel();
        transit.setLayout(new BoxLayout(transit, BoxLayout.Y_AXIS));        
        transit.setBorder(BorderFactory.createTitledBorder("Transit"));

        JCheckBox transitActive = new JCheckBox("Active", true);
        JComboBox<String> transitMode = new JComboBox<>(new String[]{"Push", "Pull"});
        transitMode.setMaximumSize(new Dimension(80, 25));
        JPanel transitTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        transitTop.add(transitActive);
        transitTop.add(new JLabel("Mode: "));
        transitTop.add(transitMode);
        transit.add(transitTop);
        add(transit);

        weatherMode.addActionListener(e ->{
            boolean push = weatherMode.getSelectedItem().equals("Push");
            weatherSubject.setMode(push);
            Diagnostics.get().setMode(Channel.WEATHER.toString(), weatherMode.getSelectedItem().toString());
        });

        aqMode.addActionListener(e ->{
            boolean push = aqMode.getSelectedItem().equals("Push");
            airSubject.setMode(push);
            Diagnostics.get().setMode(Channel.AIR_QUALITY.toString(), aqMode.getSelectedItem().toString());
        });

        alertsMode.addActionListener(e ->{
            boolean push = alertsMode.getSelectedItem().equals("Push");
            alertsSubject.setMode(push);
            Diagnostics.get().setMode(Channel.ALERTS.toString(), alertsMode.getSelectedItem().toString());
        });

        transitMode.addActionListener(e ->{
            boolean push = transitMode.getSelectedItem().equals("Push");
            mapPanel.setMode(push);
            Diagnostics.get().setMode(Channel.TRANSIT.toString(), transitMode.getSelectedItem().toString());
        });

        weatherActive.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                weatherSubject.register(weatherPanel, Channel.WEATHER);
            }
            else
            {
                weatherSubject.unregister(weatherPanel, Channel.WEATHER);
            }
        });

        aqActive.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                airSubject.register(airPanel, Channel.AIR_QUALITY);
            }
            else
            {
                airSubject.unregister(airPanel, Channel.AIR_QUALITY);
            }
        });

        alertsActive.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                alertsSubject.register(alertsPanel, Channel.ALERTS);
            }
            else
            {
                alertsSubject.unregister(alertsPanel, Channel.ALERTS);
            }
        });

        transitActive.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                mapPanel.register(alertsPanel, Channel.ALERTS);
            }
            else
            {
                mapPanel.unregister(alertsPanel, Channel.ALERTS);
            }
        });
    }
}