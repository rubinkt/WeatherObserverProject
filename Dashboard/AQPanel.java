// WeatherPanel.java
import javax.swing.*;
import java.awt.*;

import Observers.*;
import Subjects.UISubject;
import Subjects.UIUpdate;

public class AQPanel extends JPanel implements UIObserver 
{
    private final JLabel label = new JLabel();

    public AQPanel() 
    {
        setLayout(new BorderLayout());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 18f));
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Air Quality"));
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