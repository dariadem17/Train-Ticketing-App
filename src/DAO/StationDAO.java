package DAO;

import Model.Station;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDAO {

    public List<Station> getAll() {
        List<Station> stations = new ArrayList<>();
        String sql = "SELECT id, name FROM stations ORDER BY name ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                stations.add(new Station(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public void addStation(String name) throws SQLException {
        String sql = "INSERT INTO stations (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }
}