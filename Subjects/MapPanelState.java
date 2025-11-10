package Subjects;


public class MapPanelState implements UIUpdate {
    private int circleX;
    private int circleY;

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
