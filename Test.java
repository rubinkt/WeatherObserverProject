import Enums.Channel;
import Observers.WeatherPanel;
import Subjects.WeatherSubject;

public class Test {
    public static void main(String[] args) {
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
    }
}
