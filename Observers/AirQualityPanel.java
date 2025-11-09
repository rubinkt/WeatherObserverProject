package Observers;

import Subjects.AirQualitySubject;
import Subjects.AirQualitySubjectState;
import Subjects.UISubject;
import Subjects.UIUpdate;

public class AirQualityPanel implements UIObserver {
    private double airParticles;
    private double ozone;

public void update(UIUpdate u) {
    AirQualitySubjectState state = (AirQualitySubjectState) u;
    this.airParticles = state.getAirParticles();
    this.ozone = state.getOzone();
}

public void onNotified(UISubject subj) {
    AirQualitySubject airQuality = (AirQualitySubject) subj;
    AirQualitySubjectState state = airQuality.getState();
    this.airParticles = state.getAirParticles();
    this.ozone = state.getOzone();
}

public void print() {
    System.out.println("Air Quality - Air Particles: " + airParticles + ", Ozone: " + ozone);
}
}
