package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JPanel {
    public JTextField userField = new JTextField();
    public JPasswordField passField = new JPasswordField();
    public JButton loginBtn = new JButton("Login");

    public LoginView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(50, 50, 50, 50));

        Dimension fieldSize = new Dimension(200, 30);
        userField.setMaximumSize(fieldSize);
        passField.setMaximumSize(fieldSize);

        add(new JLabel("User:"));
        add(userField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Password:"));
        add(passField);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(loginBtn);
    }

    public JPanel getMainPanel() {
        return this;
    }
}