import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import InputSliders.AirParticlesSlider;
import InputSliders.TemperatureSlider;
import Subjects.AirQualitySubject;
import Subjects.MapPanel;
import Subjects.WeatherSubject;

public class ControlsPanel extends JPanel {
    private JComboBox<String> weatherStateBox;
    private JPanel weatherStatePanel;
    private WeatherSubject weatherSubject;

    private JPanel airQualityStatePanel;
    
    private JPanel mapPanel;

    private AirQualitySubject airQualitySubject;
    private MapPanel mapSubject;

    public ControlsPanel(WeatherSubject weatherSubject, AirQualitySubject airQualitySubject, MapPanel mapSubject) {
        this.weatherSubject = weatherSubject;
        this.airQualitySubject = airQualitySubject;
        this.mapSubject = mapSubject;

        airQualityStatePanel = new JPanel();
        airQualityStatePanel.setLayout(new BoxLayout(airQualityStatePanel, BoxLayout.X_AXIS));

        mapPanel = new JPanel();
        mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.X_AXIS));

        // Set appearance information
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 700));
        setBorder(BorderFactory.createTitledBorder("Subject Information"));
        
        // Create panels
        createWeatherPanel();
        createAirQualityPanel();
        // createMapPanel(); // TODO
    }
    
    private void createWeatherPanel() {
        // Define the weather panel
        weatherStatePanel = new JPanel();
        weatherStatePanel.setLayout(new BoxLayout(weatherStatePanel, BoxLayout.X_AXIS));

        // Add a new temperature slider
        this.add(new TemperatureSlider(this.weatherSubject));

        // Add a box to set the sky condition
        weatherStateBox = new JComboBox<>(new String[]{"Sunny", "Overcast", "Raining", "Snowing"});
        weatherStateBox.addActionListener(e -> {
            this.weatherSubject.setSkyCondition(weatherStateBox.getSelectedItem().toString());
        });

        weatherStatePanel.add(new JLabel("Weather Conditions: "));
        weatherStatePanel.add(weatherStateBox);
        this.add(weatherStatePanel);
    }

    private void createAirQualityPanel() {
        // Add an air particle slider
        add(new AirParticlesSlider(airQualitySubject));
        
        // Add an ozone slider
        // TODO
    }
}
