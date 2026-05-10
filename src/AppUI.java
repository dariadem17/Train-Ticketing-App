import Business.MainController;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame primaryStage = new JFrame();
            primaryStage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            primaryStage.setSize(900, 600);
            primaryStage.setLocationRelativeTo(null);
            MainController engine = new MainController(primaryStage);
            engine.showUserDashboard();
            primaryStage.setVisible(true);
        });
    }
}