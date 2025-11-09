import Enums.Channel;
import Observers.AirQualityPanel;
import Observers.WeatherPanel;
import Subjects.AirQualitySubject;
import Subjects.WeatherSubject;

public class Test {
    public static void main(String[] args) {
        /*
        WeatherSubject myWeatherSubject = new WeatherSubject();
        WeatherPanel myWeatherPanel = new WeatherPanel();
        myWeatherSubject.register(myWeatherPanel, Channel.WEATHER);
        myWeatherSubject.setTemp(9001);
        myWeatherPanel.print();
        myWeatherSubject.setSkyCondition("Sunny");
        myWeatherPanel.print();
        myWeatherSubject.setTemp(55);
        myWeatherPanel.print();
        myWeatherPanel.goBack(1);
        myWeatherPanel.print();
        myWeatherPanel.goBack(1);
        myWeatherPanel.print();
        myWeatherPanel.goBack(1);
        myWeatherPanel.print();
        myWeatherPanel.goBack(1);
        myWeatherPanel.print();
        */

        AirQualitySubject myAirQualitySubject = new AirQualitySubject();
        AirQualityPanel myAirQualityPanel = new AirQualityPanel();
        myAirQualitySubject.register(myAirQualityPanel, Channel.WEATHER);
        myAirQualitySubject.setAirParticles(350);
        myAirQualityPanel.print();
        myAirQualitySubject.setOzone(100);
        myAirQualityPanel.print();
        myAirQualityPanel.goBack(1);
        myAirQualityPanel.print();
        myAirQualitySubject.setAirParticles(0);
        myAirQualityPanel.print();
        myAirQualityPanel.goBack(1);
        myAirQualityPanel.print();
        myAirQualityPanel.goBack(1);

    }
}
