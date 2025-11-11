package Dashboard.InputSliders;
import Subjects.WeatherSubject;

public class TemperatureSlider extends InputSlider {

    private static final int maxTemperature = 100;
    private static final int minTemperature = 20;
    private static final int defaultTemperature = 60;

    public TemperatureSlider(WeatherSubject observer) {
        super(observer, minTemperature, maxTemperature, defaultTemperature, "Temperature: ", "");
    }

    public void updateField(int newTemp) {
        super.updateField(newTemp);
        // Update the temperature field
        ((WeatherSubject) getSubject()).setTemp(newTemp);
    }
}
