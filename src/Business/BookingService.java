package Business;

import DAO.BookingDAO;
import DAO.TrainDAO;
import Model.Booking;
import Model.Train;
import java.sql.SQLException;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();
    private TrainDAO trainDAO = new TrainDAO();
    private EmailService emailService = new EmailService();

    public boolean createBooking(int trainId, String email, int seatsRequested) throws SQLException {
        if (!emailService.isValidEmail(email)) {
            return false;
        }

        Train train = trainDAO.getById(trainId);
        if (train == null) {
            return false;
        }

        int occupied = bookingDAO.getOccupiedSeats(trainId);

        if (train.getTotalSeats() - occupied >= seatsRequested) {
            bookingDAO.insertBooking(trainId, seatsRequested, email);

            emailService.sendConfirmation(email, train.getName(), seatsRequested);

            return true;
        }

        return false;
    }
}