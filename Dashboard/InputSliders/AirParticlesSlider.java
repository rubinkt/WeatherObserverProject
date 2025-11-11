package InputSliders;
import Subjects.AirQualitySubject;

public class AirParticlesSlider extends InputSlider {
    
    // TODO: assign new values
    private static final int minAP = 0;
    private static final int maxAP = 30;
    private static final int defaultAP = 5;

    public AirParticlesSlider(AirQualitySubject observer) {
        super(observer, minAP, maxAP, defaultAP, "Air Particle Concentration: ", " ppb");
    }

    public void updateField(int newAP) {
        super.updateField(newAP);

        ((AirQualitySubject) getSubject()).setAirParticles(newAP);
    }
}
