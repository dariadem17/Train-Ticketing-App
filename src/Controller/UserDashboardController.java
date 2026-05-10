package Controller;

import Business.MainController;
import Business.BookingService;
import DAO.RouteDAO;
import DAO.StationDAO;
import Model.RouteResult;
import Model.Station;
import View.ViewUserDashboard;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class UserDashboardController {
    private ViewUserDashboard view;
    private MainController mainController;
    private RouteDAO routeDAO = new RouteDAO();
    private BookingService bookingService = new BookingService();
    private StationDAO stationDAO = new StationDAO();
    private List<Station> allStations = new ArrayList<>();
    private List<RouteResult> lastSearchResults = new ArrayList<>();
    public void refreshData() {
        this.allStations = stationDAO.getAll();

        view.fromCombo.removeAllItems();
        view.toCombo.removeAllItems();

        for (Station s : allStations) {
            view.fromCombo.addItem(s.getName());
            view.toCombo.addItem(s.getName());
        }

        view.tableModel.setRowCount(0);

        System.out.println("Refresh finalized: " + allStations.size() + " stations filled.");
    }
    public UserDashboardController(MainController mc) {
        this.mainController = mc;
        this.view = new ViewUserDashboard();
        loadStations();
        initEvents();
    }

    private void loadStations() {
        allStations = stationDAO.getAll();

        view.fromCombo.removeAllItems();
        view.toCombo.removeAllItems();

        for (Station s : allStations) {
            view.fromCombo.addItem(s.getName());
            view.toCombo.addItem(s.getName());
        }
    }

    private void initEvents() {
        view.searchBtn.addActionListener(e -> {
            int fromId = getSelectedStationId(view.fromCombo);
            int toId = getSelectedStationId(view.toCombo);

            view.tableModel.setRowCount(0);

            lastSearchResults = routeDAO.searchRoutes(fromId, toId);

            if (lastSearchResults.isEmpty()) {
                lastSearchResults = routeDAO.searchIndirectRoutes(fromId, toId);
            }

            if (lastSearchResults.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "There is no way(direct or indirect) between the stations!",
                        "Without results",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                for (RouteResult r : lastSearchResults) {
                    view.tableModel.addRow(new Object[]{
                            r.getTrainName(), r.getDepartureTime(),
                            r.getArrivalTime(), r.getTotalSeats(), r.getType()
                    });
                }
            }
        });

        view.bookBtn.addActionListener(e -> {
            RouteResult selected = getSelectedRoute();
            if (selected == null) {
                JOptionPane.showMessageDialog(view, "Choose a train!");
                return;
            }

            String email = view.emailField.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Type an email!");
                return;
            }

            try {
                boolean ok = bookingService.createBooking(selected.getTrainId(), email, 1);
                if (ok) {
                    JOptionPane.showMessageDialog(view, "Ticket succesfully booked!");
                } else {
                    JOptionPane.showMessageDialog(view, "Error: Overbooking!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        view.adminBtn.addActionListener(e -> mainController.showLogin());
    }
    private int getSelectedStationId(JComboBox<String> comboBox) {
        int selectedIndex = comboBox.getSelectedIndex();
        if (selectedIndex != -1 && selectedIndex < allStations.size()) {
            return allStations.get(selectedIndex).getId();
        }
        return -1;
    }

    private RouteResult getSelectedRoute() {
        int row = view.table.getSelectedRow();
        if (row != -1 && row < lastSearchResults.size()) {
            return lastSearchResults.get(row);
        }
        return null;
    }

    public ViewUserDashboard show() {
        loadStations();
        return view; }
}