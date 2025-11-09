package Subjects;
import javax.swing.*;

import Enums.Channel;

import java.awt.*;
import java.util.ArrayList;

import Observers.*;

public class MapPanel extends JPanel implements UISubject
{
    private boolean isPush;
    private ArrayList<UIObserver> observers;
    private final JLabel label = new JLabel();
    private int circleX = 0;
    private int circleY = 0;

    public MapPanel() 
    {
        setLayout(new BorderLayout());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 18f));
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Map"));
    }

    public void register(UIObserver o, Channel c) {
        observers.add(o);
    }

    public void unregister(UIObserver o, Channel c) {
        observers.remove(o);
    }

    public void notifyObservers(Channel c) {
        UIUpdate currentState = new MapPanelState(this);
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);
        g.fillOval(circleX, circleY, 50, 50);
    }

    public void setCirclePosition(int newX, int newY) {
        circleX = newX;
        circleY = newY;
    }

    public int getCircleX() {
        return circleX;
    }
    public int getCircleY() {
        return circleY;
    }
}