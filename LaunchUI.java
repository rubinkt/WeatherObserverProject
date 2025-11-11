// Main.java
import javax.swing.SwingUtilities;

import Dashboard.DashboardFrame;

public class LaunchUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
    }
}