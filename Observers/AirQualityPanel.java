package Observers;

import java.util.ArrayList;

import Subjects.AirQualitySubject;
import Subjects.AirQualitySubjectState;
import Subjects.UISubject;
import Subjects.UIUpdate;

public class AirQualityPanel implements UIObserver {
    private ArrayList<AirQualitySubjectState> states;
    private int stateIndex;
    private double airParticles;
    private double ozone;

    public AirQualityPanel() {
        states = new ArrayList<AirQualitySubjectState>();
        stateIndex = 0;
    }

    public void update(UIUpdate u) {
        stateIndex = states.size();
        states.add((AirQualitySubjectState) u);
        airParticles = states.get(stateIndex).getAirParticles();
        ozone = states.get(stateIndex).getOzone();
    }

    public void onNotified(UISubject subj) {
        AirQualitySubject aqs = (AirQualitySubject) subj;
        states.add(aqs.getState());
        airParticles = states.get(stateIndex).getAirParticles();
        ozone = states.get(stateIndex).getOzone();
    }

    public void goBack(int n) { // goes back n states provided there are n states saved
        if (stateIndex - n >= 0) {
            stateIndex -= n;
        }
        AirQualitySubjectState newState = states.get(stateIndex);
        airParticles = newState.getAirParticles();
        ozone = newState.getOzone();
    }

    public void print() {
        System.out.println("Air Quality - Air Particles: " + airParticles + ", Ozone: " + ozone);
    }
    }
