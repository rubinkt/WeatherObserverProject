package Subjects;

import java.util.ArrayList;
import Enums.Channel;
import Observers.UIObserver;

public class AirQualitySubject implements UISubject {
  private boolean isPush;
  private ArrayList<UIObserver> observers;
  private double airParticles; 
  private double ozone;
  
  public AirQualitySubject() {
    isPush = true;
    observers = new ArrayList<>();
}

    public void register(UIObserver o, Channel c) {
        observers.add(o);
    }

    public void unregister(UIObserver o, Channel c) {
        observers.remove(o);
    }

    public void notifyObservers(Channel c) {
        UIUpdate currentState = new AirQualitySubjectState(this);
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

    public AirQualitySubjectState getState() {
        return new AirQualitySubjectState(this);
    }

    // Update Methods
    public void setAirParticles(double val) {
        airParticles = val;
        notifyObservers(Channel.AIR_QUALITY);
    }

    public void setOzone(double val) {
        ozone = val;
        notifyObservers(Channel.AIR_QUALITY);
    }

    // Getters
    public double getAirParticles() {
        return airParticles;
    }

    public double getOzone() {
        return ozone;
    }
}
