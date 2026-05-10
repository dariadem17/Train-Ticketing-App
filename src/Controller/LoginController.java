package Controller;

import Business.MainController;
import View.LoginView;

public class LoginController {
    private LoginView view;
    private MainController mainController;

    public LoginController(MainController mc) {
        this.mainController = mc;
        this.view = new LoginView();

        view.loginBtn.addActionListener(e -> {
            String user = view.userField.getText();
            String pass = new String(view.passField.getPassword());

            if (user.equals("admin") && pass.equals("admin")) {
                mainController.showAdminDashboard();
            } else {
                mainController.showUserDashboard();
            }
        });
    }

    public LoginView show() {
        return view;
    }
}