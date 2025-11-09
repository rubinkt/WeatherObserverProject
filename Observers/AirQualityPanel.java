package Observers;

import java.util.ArrayList;

import javax.swing.JPanel;

import Subjects.AirQualitySubject;
import Subjects.AirQualitySubjectState;
import Subjects.UISubject;
import Subjects.UIUpdate;

public class AirQualityPanel extends JPanel implements UIObserver {
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

    public void changeState(int n) { // if n is negative, it goes backwards; if n is positive, it goes forward
        int potentialNewStateIndex = stateIndex + n;
        if (potentialNewStateIndex >= 0 && potentialNewStateIndex < states.size()) {
            stateIndex = potentialNewStateIndex;
            AirQualitySubjectState newState = states.get(stateIndex);
            airParticles = newState.getAirParticles();
            ozone = newState.getOzone();
        }
    }

    public void print() {
        System.out.println("Air Quality - Air Particles: " + airParticles + ", Ozone: " + ozone);
    }
    }
