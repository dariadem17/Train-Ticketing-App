package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewUserDashboard extends JPanel {
    public JTable table;
    public DefaultTableModel tableModel;

    public JComboBox<String> fromCombo = new JComboBox<>();
    public JComboBox<String> toCombo = new JComboBox<>();

    public JButton searchBtn = new JButton("Search for trains");
    public JButton adminBtn = new JButton("Login Admin");
    public JButton bookBtn = new JButton("Book ticket");
    public JTextField emailField = new JTextField();

    public ViewUserDashboard() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Train Ticketing App");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        addLabel(centerPanel, "Departure:");
        fromCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        fromCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(fromCombo);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addLabel(centerPanel, "Arrival:");
        toCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        toCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(toCombo);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        searchBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(searchBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] columnNames = {"Train", "Departure", "Arrival", "Total number of seats", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(800, 250));
        centerPanel.add(scrollPane);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        addLabel(centerPanel, "Type your email for confirmation:");
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(emailField);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        bookBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(bookBtn);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(new JSeparator());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        adminBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(adminBtn);

        add(centerPanel, BorderLayout.CENTER);

        fromCombo.setEditable(false);
        toCombo.setEditable(false);
    }

    private void addLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
    }

    public JPanel getMainPanel() { return this; }
}