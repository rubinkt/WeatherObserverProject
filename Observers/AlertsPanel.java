package Observers;

import javax.swing.*;

import Dashboard.Diagnostics;
import Enums.Channel;

import java.awt.*;
import java.util.ArrayList;
import Subjects.UISubject;
import Subjects.UIUpdate;
import Subjects.AlertsSubject;
import Subjects.AlertsSubjectState;

public class AlertsPanel extends JPanel implements UIObserver 
{
    private ArrayList<String> alerts;

    public AlertsPanel() 
    {
        // Use a grid layout: 0 rows = dynamic, 2 columns
        setLayout(new GridLayout(0, 2, 5, 5));
        setPreferredSize(new Dimension(300, 200));
        alerts = new ArrayList<String>();
    }

    // Push mode: receives UIUpdate snapshot
    public void update(UIUpdate update) 
    {
        if(update instanceof AlertsSubjectState) 
        {
            AlertsSubjectState state = (AlertsSubjectState) update;
            alerts = state.getAlerts();
            refreshAlerts();
            Diagnostics.get().recordEvent(Channel.ALERTS);
        }
    }

    // Pull mode: queries the subject for current state
    public void onNotified(UISubject subj) 
    {
        if(subj instanceof AlertsSubject) 
        {
            AlertsSubject alertsSubject = (AlertsSubject) subj;
            alerts = alertsSubject.getAlerts();
            refreshAlerts();
            Diagnostics.get().recordEvent(Channel.ALERTS);
        }
    }

    // Redraw the alerts in GUI
    private void refreshAlerts() 
    {
        removeAll(); // clear old badges
        if (alerts != null) {
            for (int x = Math.max(0, alerts.size() - 10); x < alerts.size(); x++) 
            {
                String alert = alerts.get(x);
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

    public String changeAlerts()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < alerts.size(); i++)
        {
            sb.append(alerts.get(i));
            sb.append("  ");
        }
        String ret = sb.toString();
        return ret;
    }
}
