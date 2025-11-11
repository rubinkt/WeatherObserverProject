package Subjects;

import java.util.ArrayList;

import Enums.Channel;
import Observers.UIObserver;
import javax.swing.JPanel;

/**
 * AlertsSubject sends alerts (strings) to observers.
 */
public class AlertsSubject extends JPanel implements UISubject, UIObserver 
{

    private boolean isPush;
    private ArrayList<UIObserver> observers;
    private ArrayList<String> alerts;

    public AlertsSubject() 
    {
        isPush = true;
        observers = new ArrayList<>();
        alerts = new ArrayList<>();
    }

    public void register(UIObserver o, Channel c) 
    {
        observers.add(o);
    }

    public void unregister(UIObserver o, Channel c) 
    {
        observers.remove(o);
    }
    
    public void notifyObservers(Channel c) 
    {
        UIUpdate currentState = new AlertsSubjectState(this);
        if (isPush) 
        {
            for (UIObserver o : observers) 
            {
                o.update(currentState);
            }
        } 
        else 
        {
            for (UIObserver o : observers) 
            {
                o.onNotified(this);
            }
        }
    }

    public void setMode(boolean isPush) 
    {
        this.isPush = isPush;
    }

    public AlertsSubjectState getState() 
    {
        return new AlertsSubjectState(this);
    }

    // Methods for MiniBadgeBar / other observers
    public void addAlert(String alert) 
    {
        alerts.add(alert);
        notifyObservers(Channel.ALERTS);
    }

    public void removeAlert(String alert) 
    {
        alerts.remove(alert);
        notifyObservers(Channel.ALERTS);
    }

    // Return a copy of the alerts list to prevent external modification
    public ArrayList<String> getAlerts() 
    {
        return new ArrayList<>(alerts);
    }

    public void update(UIUpdate u) 
    {
        switch(u.getClass().getName()) 
        {
            case "Subjects.AirQualitySubjectState":
                AirQualitySubjectState AQSstate = (AirQualitySubjectState) u;
                if(AQSstate.getAirParticles() > 20) 
                {
                    this.addAlert("Air Particles is above 20. | Air Particles: " + AQSstate.getAirParticles());
                }
                if(AQSstate.getOzone() > 20) 
                {
                    this.addAlert("Ozone is above 20. | Ozone: " + AQSstate.getOzone());
                }
                break;
            case "Subjects.WeatherSubjectState":
                WeatherSubjectState WSstate = (WeatherSubjectState) u;
                if(WSstate.getTemp() > 80) 
                {
                    this.addAlert("Temperature is above 80. | Temperature: " + WSstate.getTemp());
                }
                if(WSstate.getTemp() < 20) 
                {
                    this.addAlert("Temperature is below 20. | Temperature: " + WSstate.getTemp());
                }
                break;
        }
    }

    public void onNotified(UISubject subj) 
    {
        switch (subj.getClass().getName()) 
        {
            case "AirQualitySubject":
                AirQualitySubject aqs = (AirQualitySubject) subj;
                AirQualitySubjectState AQSstate = aqs.getState();
                if(AQSstate.getAirParticles() > 20) 
                {
                    this.addAlert("Air Particles is above 20. | Air Particles: " + AQSstate.getAirParticles());
                }
                if(AQSstate.getOzone() > 20) 
                {
                    this.addAlert("Ozone is above 20. | Ozone: " + AQSstate.getOzone());
                }
                break;
            case "WeatherSubject":
                WeatherSubject ws = (WeatherSubject) subj;
                WeatherSubjectState WSstate = ws.getState();
                if (WSstate.getTemp() > 80) {
                    this.addAlert("Temperature is above 80. | Temperature: " + WSstate.getTemp());
                }
                if (WSstate.getTemp() < 20) {
                    this.addAlert("Temperature is below 20. | Temperature: " + WSstate.getTemp());
                }
                break;
        }
    }
}
