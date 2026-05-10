package Business;

import Controller.AdminController;
import Controller.LoginController;
import Controller.UserDashboardController;
import javax.swing.*;

public class MainController {
    private JFrame primaryStage;

    public MainController(JFrame frame) {
        this.primaryStage = frame;
    }

    public void showLogin() {
        LoginController loginUI = new LoginController(this);
        updateStage(loginUI.show().getMainPanel(), "Login - Train Ticketing");
    }

    public void showUserDashboard() {
        UserDashboardController userUI = new UserDashboardController(this);
        userUI.refreshData();
        updateStage(userUI.show().getMainPanel(), "Client - Search and Booking");
    }

    public void showAdminDashboard() {
        AdminController adminUI = new AdminController(this);
        updateStage(adminUI.show().getMainPanel(), "Admin");
    }

    private void updateStage(JPanel panel, String title) {
        primaryStage.setContentPane(panel);
        primaryStage.setTitle(title);
        primaryStage.revalidate();
        primaryStage.repaint();
    }
}