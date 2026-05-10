package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookingView extends JPanel {
    public JLabel infoLabel = new JLabel();
    public JTextField emailField = new JTextField();
    public JTextField seatsField = new JTextField();
    public JButton confirmBtn = new JButton("Finalize booking");

    public BookingView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        Dimension fieldSize = new Dimension(Integer.MAX_VALUE, 30);

        emailField.setMaximumSize(fieldSize);
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);

        seatsField.setMaximumSize(fieldSize);
        seatsField.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        confirmBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(infoLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        add(new JLabel("Email:"));
        add(emailField);
        add(Box.createRigidArea(new Dimension(0, 10)));

        add(new JLabel("Number of seats:"));
        add(seatsField);
        add(Box.createRigidArea(new Dimension(0, 20)));

        add(confirmBtn);
    }

    public JPanel getMainPanel() {
        return this;
    }
}