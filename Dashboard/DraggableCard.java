import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DraggableCard extends JPanel {
    private final JPanel titleBar = new JPanel(new BorderLayout());
    private final JLabel titleLabel = new JLabel();
    private Point dragStart;
    private static final int RESIZE_ZONE = 12;

    public DraggableCard(String title, JComponent content) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        titleLabel.setText(title);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(4,6,4,6));
        titleBar.add(titleLabel, BorderLayout.WEST);
        titleBar.setBackground(new Color(0,0,0,0));
        add(titleBar, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        MouseAdapter ma = new MouseAdapter() {
            private boolean resizing = false;

            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = SwingUtilities.convertPoint(DraggableCard.this, e.getPoint(), getParent());
                resizing = isInResizeZone(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point now = SwingUtilities.convertPoint(DraggableCard.this, e.getPoint(), getParent());
                if (resizing) {
                    int newW = Math.max(120, now.x - getX());
                    int newH = Math.max(80, now.y - getY());
                    setPreferredSize(new Dimension(newW, newH));
                    revalidate();
                } else {
                    int dx = now.x - dragStart.x;
                    int dy = now.y - dragStart.y;
                    setLocation(getX() + dx, getY() + dy);
                    dragStart = now;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                setCursor(isInResizeZone(e.getPoint()) ? Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR) : Cursor.getDefaultCursor());
            }
        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
        // Improve hit-box: also let titleBar start drag
        titleBar.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { ma.mousePressed(e); }
        });
        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseDragged(MouseEvent e) { ma.mouseDragged(e); }
            @Override public void mouseMoved(MouseEvent e) { ma.mouseMoved(e); }
        });
    }

    private boolean isInResizeZone(Point p) {
        return p.x >= getWidth() - RESIZE_ZONE && p.y >= getHeight() - RESIZE_ZONE;
    }
}