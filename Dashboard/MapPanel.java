// WeatherPanel.java
import javax.swing.*;
import java.awt.*;

import Observers.*;
import Subjects.UISubject;
import Subjects.UIUpdate;

public class MapPanel extends JPanel implements UIObserver 
{
    private final JLabel label = new JLabel();

    public MapPanel() 
    {
        setLayout(new BorderLayout());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 18f));
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Map"));
    }

    @Override
    public void update(UIUpdate update) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void onNotified(UISubject subj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onNotified'");
    }
}