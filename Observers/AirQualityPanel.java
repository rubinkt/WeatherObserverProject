package Observers;

import java.util.ArrayList;

import javax.swing.JPanel;

import Dashboard.Diagnostics;
import Enums.Channel;
import Subjects.AirQualitySubject;
import Subjects.AirQualitySubjectState;
import Subjects.UISubject;
import Subjects.UIUpdate;


public class AirQualityPanel extends JPanel implements UIObserver {
    private static final int STATES_LIMIT = 300;

    private ArrayList<AirQualitySubjectState> states;
    private int stateIndex;
    private double airParticles;
    private double ozone;

    public AirQualityPanel() {
        states = new ArrayList<AirQualitySubjectState>(STATES_LIMIT);
        stateIndex = -1;
        airParticles = 5;
        ozone = 4;
    }

    public void update(UIUpdate u) {
        stateIndex = states.size() - 1;
        if (stateIndex < 0) stateIndex = 0;
        if (stateIndex == STATES_LIMIT) states.remove(0);
        states.add((AirQualitySubjectState) u);
        airParticles = states.get(stateIndex).getAirParticles();
        ozone = states.get(stateIndex).getOzone();
        Diagnostics.get().recordEvent(Channel.AIR_QUALITY);
    }

    public void onNotified(UISubject subj) {
        stateIndex = states.size() - 1;
        if (stateIndex < 0) stateIndex = 0;
        if (stateIndex == STATES_LIMIT) states.remove(0);
        AirQualitySubject aqs = (AirQualitySubject) subj;
        states.add(aqs.getState());
        airParticles = states.get(stateIndex).getAirParticles();
        ozone = states.get(stateIndex).getOzone();
        Diagnostics.get().recordEvent(Channel.AIR_QUALITY);
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

    public String updateAirParticlesString() 
    {
        return "  Air Particles: " + airParticles + " ppb";
    }

    public String updateOzoneString() {
        return "  Ozone: " + ozone + " μg/cubic meter";
    }
}
