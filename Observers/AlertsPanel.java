package Observers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import Subjects.UISubject;
import Subjects.UIUpdate;
import Subjects.AlertsSubject;
import Subjects.AlertsSubjectState;

public class AlertsPanel extends JPanel implements UIObserver {

    private List<String> alerts;

    public AlertsPanel() {
        // Use a grid layout: 0 rows = dynamic, 2 columns
        setLayout(new GridLayout(0, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Alerts Panel"));
        setPreferredSize(new Dimension(300, 200));
    }

    // Push mode: receives UIUpdate snapshot
    public void update(UIUpdate update) {
        if (update instanceof AlertsSubjectState) {
            AlertsSubjectState state = (AlertsSubjectState) update;
            alerts = state.getAlerts();
            refreshAlerts();
        }
    }

    // Pull mode: queries the subject for current state
    public void onNotified(UISubject subj) {
        if (subj instanceof AlertsSubject) {
            AlertsSubject alertsSubject = (AlertsSubject) subj;
            alerts = alertsSubject.getAlerts();
            refreshAlerts();
        }
    }

    // Redraw the alerts in GUI
    private void refreshAlerts() {
        removeAll(); // clear old badges
        if (alerts != null) {
            for (String alert : alerts) {
                JLabel badge = new JLabel(alert, SwingConstants.CENTER);
                badge.setOpaque(true);
                badge.setBackground(Color.RED);
                badge.setForeground(Color.WHITE);
                badge.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                badge.setPreferredSize(new Dimension(100, 30));
                add(badge);
            }
        }
        revalidate();
        repaint();
    }
}
