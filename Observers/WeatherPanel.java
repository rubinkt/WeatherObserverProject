package Observers;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

import Subjects.UISubject;
import Subjects.UIUpdate;
import Subjects.WeatherSubject;
import Subjects.WeatherSubjectState;

public class WeatherPanel extends JPanel implements UIObserver{
    private ArrayList<WeatherSubjectState> states;
    private int stateIndex;
    private double temp;
    private String skyCondition;

    public WeatherPanel() {
        states = new ArrayList<WeatherSubjectState>();
        stateIndex = 0;
    }

    public void update(UIUpdate u) {
        stateIndex = states.size();
        states.add((WeatherSubjectState) u);
        temp = states.get(stateIndex).getTemp();
        skyCondition = states.get(stateIndex).getSkyCondition();
    }

    public void onNotified(UISubject subj) {
        WeatherSubject ws = (WeatherSubject) subj;
        states.add(ws.getState());
        temp = states.get(stateIndex).getTemp();
        skyCondition = states.get(stateIndex).getSkyCondition();
    }

    public void goBack(int n) { // goes back n states provided there are n states saved
        if (stateIndex - n >= 0) {
            stateIndex -= n;
        }
        WeatherSubjectState newState = states.get(stateIndex);
        temp = newState.getTemp();
        skyCondition = newState.getSkyCondition();
    }
    
    public void print() {
        System.out.println("temp is " + temp + " and sky is " + skyCondition);
    }
}
