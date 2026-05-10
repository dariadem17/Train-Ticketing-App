package Controller;

import Business.BookingService;
import Model.RouteResult;
import View.BookingView;
import javax.swing.*;
import java.sql.SQLException;

public class BookingController {
    private BookingView view;
    private BookingService bookingService = new BookingService();
    private RouteResult selectedRoute;
    private JFrame frame;

    public BookingController(RouteResult route) {
        this.selectedRoute = route;
        this.view = new BookingView();

        view.infoLabel.setText("Train: " + route.getTrainName() + " (" + route.getType() + ")");

        initEvents();
    }

    private void initEvents() {
        view.confirmBtn.addActionListener(e -> {
            try {
                String email = view.emailField.getText().trim();
                String seatsText = view.seatsField.getText().trim();

                if (email.isEmpty() || seatsText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int seats = Integer.parseInt(seatsText);

                boolean success = bookingService.createBooking(
                        selectedRoute.getTrainId(),
                        email,
                        seats
                );

                if (success) {
                    JOptionPane.showMessageDialog(frame, "Booking confirmed! A ticket has been sent to " + email);
                    if (frame != null) {
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Booking failed. Not enough seats or invalid email.", "Warning", JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for seats.", "Format Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    public void show() {
        frame = new JFrame("Confirm Booking");
        frame.setContentPane(view);
        frame.pack();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}