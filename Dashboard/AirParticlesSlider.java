import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Subjects.AirQualitySubject;

public class AirParticlesSlider extends JPanel {
    private final JSlider airParticlesSlider = new JSlider(minAP, maxAP, defaultAP);
    private final JLabel airParticlesLabel = new JLabel();

    // TODO: assign new values
    private static final int minAP = 0;
    private static final int maxAP = 20;
    private static final int defaultAP = 5;

    private int currentAP;
    private AirQualitySubject observer;

    public AirParticlesSlider(AirQualitySubject observer) {
        setLayout(new BorderLayout());
        this.observer = observer;

        updateAP(defaultAP);

        airParticlesSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                updateAP(((JSlider)e.getSource()).getValue());
            }
        });

        add(airParticlesLabel, BorderLayout.WEST);
        add(airParticlesSlider, BorderLayout.CENTER);
    }

    private void updateAP(int newAP) {
        currentAP = newAP;
        airParticlesLabel.setText("Air Particle Concentration: " + currentAP + " Parts per billion");
        observer.setAirParticles(newAP);
    }
}
