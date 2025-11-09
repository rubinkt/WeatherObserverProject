// Main.java
import javax.swing.SwingUtilities;

public class LaunchUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
    }
}