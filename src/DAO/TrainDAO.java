package DAO;

import Model.Train;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {
    public Train getById(int id) {
        String sql = "SELECT * FROM trains WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Train(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("route_id"),
                        rs.getInt("total_seats"),
                        rs.getInt("delay_minutes")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addTrain(String name, int routeId, int totalSeats) throws SQLException {
        String sql = "INSERT INTO trains (name, route_id, total_seats, delay_minutes) VALUES (?, ?, ?, 0)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, routeId);
            ps.setInt(3, totalSeats);
            ps.executeUpdate();
        }
    }

    public void deleteTrain(int id) throws SQLException {
        String sql = "DELETE FROM trains WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public void updateDelay(int trainId, int minutes) throws SQLException {
        String sql = "UPDATE trains SET delay_minutes = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, minutes);
            ps.setInt(2, trainId);
            ps.executeUpdate();
        }
    }

    public List<Train> getAllTrains() {
        List<Train> trains = new ArrayList<>();
        String sql = "SELECT * FROM trains";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                trains.add(new Train(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("route_id"),
                        rs.getInt("total_seats"),
                        rs.getInt("delay_minutes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trains;
    }
}