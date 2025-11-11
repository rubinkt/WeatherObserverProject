package InputSliders;
import Subjects.WeatherSubject;

public class TemperatureSlider extends InputSlider {

    private static final int maxTemperature = 100;
    private static final int minTemperature = 0;
    private static final int defaultTemperature = 50;

    public TemperatureSlider(WeatherSubject observer) {
        super(observer, minTemperature, maxTemperature, defaultTemperature, "Temperature: ", "");
    }

    public void updateField(int newTemp) {
        super.updateField(newTemp);
        // Update the temperature field
        ((WeatherSubject) getSubject()).setTemp(newTemp);
    }
}
