package Subjects;

public class WeatherSubjectState implements UIUpdate{
    private double temp;
    private String skyCondition;
    
    public WeatherSubjectState(WeatherSubject ws) {
        this.temp = ws.getTemp();
        this.skyCondition = ws.getSkyCondition();
    }

    public double getTemp() {
        return temp;
    }
    public String getSkyCondition() {
        return skyCondition;
    }
}
