package Dashboard.InputSliders;

import Subjects.MapPanel;

public class MapXSlider extends InputSlider {
  
  private static final int minX = 0;
  private static final int maxX = 280;
  private static final int defaultX = 0;

  public MapXSlider(MapPanel observer) {
    super(observer, minX, maxX, defaultX, "Map Latitude: ", " degrees");
  }

  @Override
  public void updateField(int newValue) {
    super.updateField(newValue);
    // Update the X position
    ((MapPanel) getSubject()).setCircleX(newValue);
  }
}
