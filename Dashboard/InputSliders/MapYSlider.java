package InputSliders;

import Subjects.MapPanel;

public class MapYSlider extends InputSlider {
  
  private static final int minY = 0;
  private static final int maxY = 300;
  private static final int defaultY = 0;

  public MapYSlider(MapPanel observer) {
    super(observer, minY, maxY, defaultY, "Map Longitude: ", " degrees");
  }

  @Override
  public void updateField(int newValue) {
    super.updateField(newValue);
    // Update the Y position
    ((MapPanel) getSubject()).setCircleY(newValue);
  }
}
