package Subjects;

import java.util.ArrayList;
import java.util.List;

import Enums.Channel;
import Observers.UIObserver;
import javax.swing.JPanel;

/**
 * AlertsSubject sends alerts (strings) to observers.
 */
public class AlertsSubject extends JPanel implements UISubject {

    private boolean isPush = true;
    private List<UIObserver> observers;
    private List<String> alerts;

    public AlertsSubject() {
        isPush = true;
        observers = new ArrayList<>();
        alerts = new ArrayList<>();
    }

    public void register(UIObserver o, Channel c) {
        observers.add(o);
    }

    public void unregister(UIObserver o, Channel c) {
        observers.remove(o);
    }
    
    public void notifyObservers(Channel c) {
        UIUpdate currentState = new AlertsSubjectState(this);
        if (isPush) {
            for (UIObserver o : observers) {
                o.update(currentState);
            }
        } else {
            for (UIObserver o : observers) {
                o.onNotified(this);
            }
        }
    }

    public void setMode(boolean isPush) {
        this.isPush = isPush;
    }

    public AlertsSubjectState getState() {
    return new AlertsSubjectState(this);
    }

    // Methods for MiniBadgeBar / other observers
    public void addAlert(String alert) {
        alerts.add(alert);
        notifyObservers(Channel.ALERTS);
    }

    public void removeAlert(String alert) {
        alerts.remove(alert);
        notifyObservers(Channel.ALERTS);
    }

    // Return a copy of the alerts list to prevent external modification
    public List<String> getAlerts() {
        return new ArrayList<>(alerts);
    }
}
