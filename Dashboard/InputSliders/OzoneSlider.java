package InputSliders;

import Subjects.AirQualitySubject;

public class OzoneSlider extends InputSlider {
    
    private static final int minOzone = 0;
    private static final int maxOzone = 8;
    private static final int defaultOzone = 4;

    public OzoneSlider(AirQualitySubject observer) {
        super(observer, minOzone, maxOzone, defaultOzone, "Ozone Concentration: ", " μg/cubic meter");
    }

    public void updateField(int newConcentration) {
        super.updateField(newConcentration);
        ((AirQualitySubject) getSubject()).setOzone(newConcentration);
    }
}
