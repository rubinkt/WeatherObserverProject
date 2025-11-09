package Observers;

import javax.swing.JPanel;

import Subjects.UISubject;
import Subjects.UIUpdate;
import Subjects.WeatherSubject;
import Subjects.WeatherSubjectState;

public class WeatherPanel extends JPanel implements UIObserver{
    private WeatherSubjectState state;
    private double temp;
    private String skyCondition;


    public void update(UIUpdate u) {
        state = (WeatherSubjectState) u;
        temp = state.getTemp();
        skyCondition = state.getSkyCondition();
    }

    public void onNotified(UISubject subj) {
        WeatherSubject ws = (WeatherSubject) subj;
        state = ws.getState();
        temp = state.getTemp();
        skyCondition = state.getSkyCondition();
    }
    
    public void print() {
        System.out.println("temp is " + temp + " and sky is " + skyCondition);
    }
}
