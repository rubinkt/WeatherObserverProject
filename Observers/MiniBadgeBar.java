package Observers;

import javax.swing.*;
import java.awt.*;

import Subjects.UIUpdate;
import Subjects.UISubject;
import Subjects.AlertsSubject;
import Subjects.AlertsSubjectState;

public class MiniBadgeBar extends JPanel implements UIObserver {
    private int alertsReceived = 0;

    public MiniBadgeBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        //setBackground(new Color(0, 0, 0, 0)); // Transparent background
        setBorder(BorderFactory.createTitledBorder("Mini Badge Bar"));
        setPreferredSize(new Dimension(400, 60));
        alertsReceived = 0;
        refreshCount();
    }

    public void update(UIUpdate update) {
        if (update instanceof AlertsSubjectState) {
            alertsReceived++;
            refreshCount();
        }
    }

    public void onNotified(UISubject subj) {
        if (subj instanceof AlertsSubject) {
            alertsReceived++;
            refreshCount();
        }
        }

    private void refreshCount() {
        removeAll();
        JLabel alertCount = new JLabel("" + alertsReceived);
        add(alertCount);
        revalidate();
        repaint();
    }
    
}
