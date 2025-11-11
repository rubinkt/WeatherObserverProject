package Dashboard.InputSliders;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Subjects.UISubject;

public abstract class InputSlider extends JPanel {
    private JSlider valueSlider;
    private JLabel valueLabel;

    private int currentValue;

    private String labelTopic;
    private String labelUnits;

    private UISubject observer;

    public InputSlider(UISubject observer, int minValue, int maxValue, int defaultValue, String labelTopic, String labelUnits) {
        // Initialize fields
        this.observer = observer;
        
        currentValue = defaultValue;

        this.labelTopic = labelTopic;
        this.labelUnits = labelUnits;

        // Configure layout
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Construct label and slider
        valueLabel = new JLabel();
        
        valueSlider = new JSlider(minValue, maxValue, defaultValue);
        valueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateField(((JSlider)e.getSource()).getValue());
            }
        });

        updateField(defaultValue);

        add(valueLabel, BorderLayout.WEST);
        add(valueSlider, BorderLayout.CENTER);
    }

    /** Updates a field to have a new value.
     * CALL THIS IN IMPLEMENTATIONS using super.
     * 
     * @param newValue
     */
    public void updateField(int newValue) {
        currentValue = newValue;
        valueLabel.setText(labelTopic + currentValue + labelUnits);
    }

    protected UISubject getSubject() {
        return observer;
    }
}
