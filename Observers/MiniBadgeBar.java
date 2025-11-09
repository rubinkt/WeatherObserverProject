package Observers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import Subjects.UIUpdate;
import Subjects.UISubject;
import Subjects.AlertsSubject;
import Subjects.AlertsSubjectState;

public class MiniBadgeBar extends JPanel implements UIObserver {
    public MiniBadgeBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        //setBackground(new Color(0, 0, 0, 0)); // Transparent background
        setBorder(BorderFactory.createTitledBorder("Mini Badge Bar"));
        setPreferredSize(new Dimension(400, 60));
    }

    public void update(UIUpdate update) {
        if (update instanceof AlertsSubjectState) {
            List<String> alerts = ((AlertsSubjectState) update).getAlerts();
            refreshBadges(alerts);
        }
    }

    public void onNotified(UISubject subj) {
        if (subj instanceof AlertsSubject) {
            List<String> alerts = ((AlertsSubject) subj).getAlerts();
            refreshBadges(alerts);
        }
        }

    private void refreshBadges(List<String> alerts) {
        removeAll();
        for (String alert : alerts) {
            JLabel badge = new JLabel(alert);
            badge.setOpaque(true);
            badge.setBackground(Color.RED);
            badge.setForeground(Color.WHITE);
            badge.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
            add(badge);
        }
        revalidate();
        repaint();
    }
    
}
