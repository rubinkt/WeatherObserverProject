import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Subjects.WeatherSubject;

public class TemperatureSlider extends JPanel {
    private final JSlider temperatureSlider = new JSlider(minTemperature, maxTemperature, 50);
    private JLabel temperatureLabel;
    private int currentTemperature;
    private static final int maxTemperature = 100;
    private static final int minTemperature = 20;
    private static final int defaultTemperature = 60;
    private WeatherSubject observer;

    public TemperatureSlider(WeatherSubject observer) {
        setLayout(new BorderLayout());
        this.observer = observer;

        temperatureLabel = new JLabel("Temperature: " + defaultTemperature);
        
        temperatureSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateTemperature(((JSlider)e.getSource()).getValue());
            }
        });

        add(temperatureLabel, BorderLayout.WEST);
        add(temperatureSlider, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Weather Slider"));
    }

    private void updateTemperature(int newTemp) {
        currentTemperature = newTemp;
        temperatureLabel.setText("Temperature: " + currentTemperature);
        observer.setTemp(newTemp);
    }
}
