package Observers;

import java.util.ArrayList;

import javax.swing.JPanel;

import Subjects.UISubject;
import Subjects.UIUpdate;
import Subjects.WeatherSubject;
import Subjects.WeatherSubjectState;

public class WeatherPanel extends JPanel implements UIObserver{
    private static final int STATES_LIMIT = 300;

    private ArrayList<WeatherSubjectState> states;
    private int stateIndex;
    private double temp;
    private String skyCondition;

    public WeatherPanel() {
        states = new ArrayList<WeatherSubjectState>(STATES_LIMIT);
        stateIndex = -1;
    }

    public void update(UIUpdate u) {
        stateIndex = states.size() - 1;
        if (stateIndex < 0) stateIndex = 0;
        if (stateIndex == STATES_LIMIT) states.remove(0);
        states.add((WeatherSubjectState) u);
        temp = states.get(stateIndex).getTemp();
        skyCondition = states.get(stateIndex).getSkyCondition();
    }

    public void onNotified(UISubject subj) {
        stateIndex = states.size() - 1;
        if (stateIndex < 0) stateIndex = 0;
        if (stateIndex == STATES_LIMIT) states.remove(0);
        WeatherSubject ws = (WeatherSubject) subj;
        states.add(ws.getState());
        temp = states.get(stateIndex).getTemp();
        skyCondition = states.get(stateIndex).getSkyCondition();
    }

    public void changeState(int n) { // if n is negative, it goes backwards; if n is positive, it goes forward
        int potentialNewStateIndex = stateIndex + n;
        if (potentialNewStateIndex >= 0 && potentialNewStateIndex < states.size()) {
            stateIndex = potentialNewStateIndex;
            WeatherSubjectState newState = states.get(stateIndex);
            temp = newState.getTemp();
            skyCondition = newState.getSkyCondition();
        }
    }
    
    public void print() {
        System.out.println("temp is " + temp + " and sky is " + skyCondition);
    }
}
