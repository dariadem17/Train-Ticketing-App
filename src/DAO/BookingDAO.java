package DAO;

import Model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public int getOccupiedSeats(int trainId) {
        String sql = "SELECT SUM(seats_booked) FROM bookings WHERE train_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trainId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) { return 0; }
    }

    public void insertBooking(int trainId, int seats, String email) throws SQLException {
        String sql = "INSERT INTO bookings (train_id, seats_booked, customer_email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trainId);
            ps.setInt(2, seats);
            ps.setString(3, email);
            ps.executeUpdate();
        }
    }

    public List<String> getEmailsForTrain(int trainId) {
        List<String> emails = new ArrayList<>();
        String sql = "SELECT DISTINCT customer_email FROM bookings WHERE train_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trainId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { emails.add(rs.getString(1)); }
        } catch (Exception e) { }
        return emails;
    }
    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while(rs.next()) {

                list.add(new Booking(
                        rs.getInt("train_id"),
                        rs.getInt("seats_booked"),
                        rs.getString("customer_email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
