package View;

import Model.Train;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminView extends JPanel {
    public JList<Train> trainList = new JList<>();
    public JTextField delayField = new JTextField(5);
    public JTextField nameField = new JTextField(10);
    public JTextField routeIdField = new JTextField(5);
    public JTextField seatsField = new JTextField(5);
    public JTextField newRouteField = new JTextField(10);
    public JTextField newStationField = new JTextField(10);
    public JButton addStationBtn = new JButton("Add a new station");
    public JButton addRouteBtn = new JButton("Create a new route");
    public JButton updateBtn = new JButton("Update Delay & Notify");
    public JButton addBtn = new JButton("Add a train");
    public JButton deleteBtn = new JButton("Delete train");
    public JButton viewBookingsBtn = new JButton("See all the bookings");
    public JButton backBtn = new JButton("Logout");

    public AdminView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        setupComponents();
        createLayout();
    }

    private void setupComponents() {
        Dimension fieldSize = new Dimension(Integer.MAX_VALUE, 30);
        delayField.setMaximumSize(fieldSize);
        nameField.setMaximumSize(fieldSize);
        routeIdField.setMaximumSize(fieldSize);
        seatsField.setMaximumSize(fieldSize);
    }

    private void createLayout() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Active trains:"), BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(trainList), BorderLayout.CENTER);
        leftPanel.add(deleteBtn, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        centerPanel.add(new JLabel("Set delay (min):"));
        centerPanel.add(delayField);
        centerPanel.add(updateBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(new JSeparator());
        centerPanel.add(new JLabel("--- Add new station ---"));
        centerPanel.add(new JLabel("Stations's name:"));
        newStationField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        centerPanel.add(newStationField);
        centerPanel.add(addStationBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(new JLabel("--- Add new route ---"));
        centerPanel.add(new JLabel("Route's name:"));
        newRouteField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        centerPanel.add(newRouteField);
        centerPanel.add(addRouteBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(new JSeparator());
        centerPanel.add(new JLabel("--- Add new train ---"));
        centerPanel.add(new JLabel("Name:")); centerPanel.add(nameField);
        centerPanel.add(new JLabel("ID Route:")); centerPanel.add(routeIdField);
        centerPanel.add(new JLabel("Seats:")); centerPanel.add(seatsField);
        centerPanel.add(addBtn);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(viewBookingsBtn);

        add(centerPanel, BorderLayout.CENTER);
        add(backBtn, BorderLayout.SOUTH);
    }


    public void setTrainListData(List<Train> trains) {
        DefaultListModel<Train> model = new DefaultListModel<>();
        for (Train t : trains) model.addElement(t);
        trainList.setModel(model);
    }

    public void showBookingsPopup(String content) {
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scroll, "Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showError(String err) {
        JOptionPane.showMessageDialog(this, err, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public JPanel getMainPanel() {
        return this;
    }
}