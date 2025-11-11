package Subjects;

import java.util.ArrayList;

public class AlertsSubjectState implements UIUpdate {

    private ArrayList<String> alerts;

    public AlertsSubjectState(AlertsSubject subj) 
    {
        this.alerts = subj.getAlerts();
    }

    public ArrayList<String> getAlerts() 
    {
        return alerts;
    }
}
