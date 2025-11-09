import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Subjects.WeatherSubject;

public class ControlsPanel extends JPanel {

    public ControlsPanel(WeatherSubject weatherSubject) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 700));
        this.add(new JLabel("Weather Controls"));
        this.add(new TemperatureSlider(weatherSubject));
    }
    
}
