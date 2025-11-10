import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import InputSliders.AirParticlesSlider;
import InputSliders.OzoneSlider;
import InputSliders.TemperatureSlider;
import Subjects.AirQualitySubject;
import Subjects.MapPanel;
import Subjects.WeatherSubject;

public class ControlsPanel extends JPanel {
    private JComboBox<String> weatherStateBox;
    private JPanel weatherPanel;
    private WeatherSubject weatherSubject;

    private JPanel airQualityStatePanel;
    
    private JPanel mapPanel;

    private AirQualitySubject airQualitySubject;
    private MapPanel mapSubject;

    public ControlsPanel(WeatherSubject weatherSubject, AirQualitySubject airQualitySubject, MapPanel mapSubject) {
        this.weatherSubject = weatherSubject;
        this.airQualitySubject = airQualitySubject;
        this.mapSubject = mapSubject;

        mapPanel = new JPanel();
        mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));

        // Set appearance information
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 700));
        setBorder(BorderFactory.createTitledBorder("Subject Information"));
        
        // Create panels
        createWeatherPanel();
        createAirQualityPanel();
        // createMapPanel(); // TODO
    }
    
    private void createWeatherPanel() {
        // Define the weather panel
        weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        weatherPanel.setBorder(BorderFactory.createTitledBorder("Weather"));

        // Add a new temperature slider
        weatherPanel.add(new TemperatureSlider(this.weatherSubject));

        // Add a box to set the sky condition
        JPanel weatherStatePanel = new JPanel();
        weatherStateBox = new JComboBox<>(new String[]{"Sunny", "Overcast", "Raining", "Snowing"});
        weatherStateBox.addActionListener(e -> {
            this.weatherSubject.setSkyCondition(weatherStateBox.getSelectedItem().toString());
        });

        weatherStatePanel.add(new JLabel("Weather Conditions: "));
        weatherStatePanel.add(weatherStateBox);
        weatherPanel.add(weatherStatePanel);
        this.add(weatherPanel);
    }

    private void createAirQualityPanel() {
        airQualityStatePanel = new JPanel();
        airQualityStatePanel.setLayout(new BoxLayout(airQualityStatePanel, BoxLayout.Y_AXIS));
        airQualityStatePanel.setBorder(BorderFactory.createTitledBorder("Air Quality"));
        
        // Add an air particle slider
        airQualityStatePanel.add(new AirParticlesSlider(airQualitySubject));
        
        // Add an ozone slider
        airQualityStatePanel.add(new OzoneSlider(airQualitySubject));

        add(airQualityStatePanel);
    }
}
