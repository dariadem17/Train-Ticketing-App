package Controller;

import Business.MainController;
import Business.EmailService;
import DAO.RouteDAO;
import DAO.StationDAO;
import DAO.TrainDAO;
import DAO.BookingDAO;
import Model.Train;
import Model.Booking;
import View.AdminView;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private AdminView view;
    private MainController mainController;
    private TrainDAO trainDAO = new TrainDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private EmailService emailService = new EmailService();
    private RouteDAO routeDAO = new RouteDAO();
    private StationDAO stationDAO = new StationDAO();
    private List<Train> allTrains;
    public AdminController(MainController mc) {
        this.mainController = mc;
        this.view = new AdminView();
        refreshData();
        initEvents();
    }

    private void initEvents() {
        view.updateBtn.addActionListener(e -> {
            Train selected = view.trainList.getSelectedValue();
            if (selected != null) {
                try {
                    int delay = Integer.parseInt(view.delayField.getText());
                    trainDAO.updateDelay(selected.getId(), delay);

                    List<String> customerEmails = bookingDAO.getEmailsForTrain(selected.getId());
                    for (String email : customerEmails) {
                        emailService.notifyDelay(email, selected.getName(), delay);
                    }

                    JOptionPane.showMessageDialog(view, "Delay saved! Clients were notified.");
                    refreshData();
                } catch (Exception ex) { JOptionPane.showMessageDialog(view, "Error!"); }
            }
        });

        view.addBtn.addActionListener(e -> {
            try {
                trainDAO.addTrain(view.nameField.getText(),
                        Integer.parseInt(view.routeIdField.getText()),
                        Integer.parseInt(view.seatsField.getText()));
                refreshData();
                JOptionPane.showMessageDialog(view, "Train added!");
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        view.viewBookingsBtn.addActionListener(e -> {
            List<Booking> allBookings = bookingDAO.getAllBookings();

            System.out.println("Found bookings: " + allBookings.size());

            if (allBookings.isEmpty()) {
                view.showMessage("No bookings in the database.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-20s | %-10s | %-10s\n", "Email", "Train ID", "Seats"));
            sb.append("------------------------------------------------------------\n");

            for (Booking b : allBookings) {
                sb.append(String.format("%-20s | %-10d | %-10d\n",
                        b.getCustomerEmail(),
                        b.getTrainId(),
                        b.getSeatsBooked()));
            }

            view.showBookingsPopup(sb.toString());
        });

        view.addRouteBtn.addActionListener(e -> {
            String routeName = view.newRouteField.getText();
            if (routeName.isEmpty()) {
                view.showError("Type a name for the route!");
                return;
            }
            try {
                int newId = routeDAO.addRoute(routeName);
                view.showMessage("Route succesfully created with id: " + newId);
                view.newRouteField.setText("");
            } catch (SQLException ex) {
                view.showError("Error at adding route: " + ex.getMessage());
            }
        });
        view.addStationBtn.addActionListener(e -> {
            String name = view.newStationField.getText();
            if (name.isEmpty()) {
                view.showError("Type the name of the station!");
                return;
            }
            try {
                stationDAO.addStation(name);
                view.showMessage("Station " + name + " was added!");
                view.newStationField.setText("");
            } catch (SQLException ex) {
                view.showError("Error: " + ex.getMessage());
            }
        });

        view.deleteBtn.addActionListener(e -> {
            int selectedIndex = view.trainList.getSelectedIndex();

            if (selectedIndex != -1) {
                Train selectedTrain = allTrains.get(selectedIndex);

                int response = JOptionPane.showConfirmDialog(view,
                        "Are you sure you want to delete " + selectedTrain.getName() + "?",
                        "Delete confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    try {
                        trainDAO.deleteTrain(selectedTrain.getId());

                        refreshData();
                        JOptionPane.showMessageDialog(view, "Train was succesfully deleted.");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(view, "Error at deleting: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Select a train from the list first.");
            }
        });
        view.backBtn.addActionListener(e -> mainController.showLogin());
    }

    private void refreshData() {
        DefaultListModel<Train> model = new DefaultListModel<>();
        for (Train t : trainDAO.getAllTrains()) { model.addElement(t); }
        view.trainList.setModel(model);
    }

    public AdminView show() { return view; }
}