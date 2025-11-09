import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Subjects.WeatherSubject;

public class ControlsPanel extends JPanel {
    private JComboBox<String> weatherStateBox;
    private JPanel weatherStatePanel;
    private WeatherSubject weatherSubject;

    public ControlsPanel(WeatherSubject weatherSubject) {
        this.weatherSubject = weatherSubject;
        this.weatherStatePanel = new JPanel();
        this.weatherStatePanel.setLayout(new BoxLayout(weatherStatePanel, BoxLayout.X_AXIS));

        // Set appearance information
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 700));
        setBorder(BorderFactory.createTitledBorder("Weather Information"));
        
        // Add a new temperature slider
        this.add(new TemperatureSlider(weatherSubject));

        // Add a box to set the sky condition
        weatherStateBox = new JComboBox<>(new String[]{"Sunny", "Overcast", "Raining", "Snowing"});
        weatherStateBox.addActionListener(e -> {
            weatherSubject.setSkyCondition(weatherStateBox.getSelectedItem().toString());
        });

        weatherStatePanel.add(new JLabel("Weather Conditions: "));
        weatherStatePanel.add(weatherStateBox);
        this.add(weatherStatePanel);
    }
    
}
