package Subjects;
import javax.swing.*;

import Enums.Channel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Observers.*;

public class MapPanel extends JPanel implements UISubject, ActionListener
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
        // setBorder(BorderFactory.createTitledBorder("Map"));
        observers = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);
        g.fillOval(circleX,  circleY, 20, 20);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        repaint();
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

    public void setCircleX(int newX) {
        this.circleX = newX;
        notifyObservers(Channel.TRANSIT);
        repaint();
    }
    public void setCircleY(int newY) {
        this.circleY = newY;
        notifyObservers(Channel.TRANSIT);
        repaint();
    }

    public int getCircleX() {
        return circleX;
    }
    public int getCircleY() {
        return circleY;
    }
}