package Observers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Subjects.UIUpdate;
import Subjects.UISubject;
import Subjects.AlertsSubject;
import Subjects.AlertsSubjectState;

public class MiniBadgeBar extends JPanel implements UIObserver {
    private int alertsReceived = 0;
    private String mostRecentAlertMessage = "No alerts";

    public MiniBadgeBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        //setBackground(new Color(0, 0, 0, 0)); // Transparent background
        setBorder(BorderFactory.createTitledBorder("Mini Badge Bar"));
        setPreferredSize(new Dimension(400, 60));
        alertsReceived = 0;
        mostRecentAlertMessage = "No alerts";
        refreshPanel();
    }

    public void update(UIUpdate update) {
        if (update instanceof AlertsSubjectState) {
            alertsReceived++;
            ArrayList<String> alerts = ((AlertsSubjectState) update).getAlerts();
            mostRecentAlertMessage = alerts.get(alerts.size() - 1);
            refreshPanel();
        }
    }

    public void onNotified(UISubject subj) {
        if (subj instanceof AlertsSubject) {
            alertsReceived++;
            ArrayList<String> alerts = ((AlertsSubject) subj).getState().getAlerts();
            mostRecentAlertMessage = alerts.get(alerts.size() - 1);
            refreshPanel();
        }
    }

    private void refreshPanel() {
        removeAll();
        JLabel alertCount = new JLabel("" + alertsReceived);
        JLabel recentAlert = new JLabel(mostRecentAlertMessage);
        add(alertCount);
        add(recentAlert);
        revalidate();
        repaint();
    }
    
}
