package Subjects;

import java.util.List;

public class AlertsSubjectState implements UIUpdate {

    private List<String> alerts;

    public AlertsSubjectState(AlertsSubject subj) {
        this.alerts = subj.getAlerts();

    }

    public List<String> getAlerts() {
        return alerts;
    }
}
