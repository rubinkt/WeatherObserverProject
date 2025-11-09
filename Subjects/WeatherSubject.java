package Subjects;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import Enums.Channel;
import Observers.UIObserver;

public class WeatherSubject extends JPanel implements UISubject {
    private boolean isPush;
    private ArrayList<UIObserver> observers;
    private double temp;
    private String skyCondition;

    public WeatherSubject() {
        isPush = true;
        observers = new ArrayList<UIObserver>();
    }

    public void register(UIObserver o, Channel c) {
        observers.add(o);
    }
    public void unregister(UIObserver o, Channel c) {
        observers.remove(o);
    }
    public void notifyObservers(Channel c) {
        UIUpdate currentState = new WeatherSubjectState(this);
        if (isPush) {
            for (UIObserver o : observers) {
                o.update(currentState);
            }
        }
        else {
            for (UIObserver o: observers) {
                o.onNotified(this);
            }
        }
    }
    public void setMode(boolean isPush) {
        this.isPush = isPush;
    }

    public WeatherSubjectState getState() {
        return new WeatherSubjectState(this);
    }

    public void setTemp(double temp) {
        this.temp = temp;
        notifyObservers(Channel.WEATHER);
    }
    public void setSkyCondition(String condition) {
        this.skyCondition = condition;
        notifyObservers(Channel.WEATHER);
    }

    public double getTemp() {
        return temp;
    }
    public String getSkyCondition() {
        return skyCondition;
    }
}
