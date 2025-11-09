package Subjects;

import java.util.List;

public class MapPanelState implements UIUpdate {
    private static int circleX;
    private static int circleY;

    public MapPanelState(MapPanel subj) {
        this.circleX = subj.getCircleX();
        this.circleY = subj.getCircleY();
    }

    public int getCircleX() {
        return circleX;
    }
    public int getCircleY() {
        return circleY;
    }
}
