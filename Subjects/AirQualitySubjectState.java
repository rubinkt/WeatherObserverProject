package Subjects;

public class AirQualitySubjectState implements UIUpdate {
    private double airParticles;
    private double ozone;

    public AirQualitySubjectState(AirQualitySubject airQuality) {
        this.airParticles = airQuality.getAirParticles();
        this.ozone = airQuality.getOzone();
    }

    public double getAirParticles() {
        return airParticles;
    }

    public double getOzone() {
        return ozone;
    }
}
