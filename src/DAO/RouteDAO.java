package DAO;

import Model.Route;
import Model.RouteResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {
    public List<RouteResult> searchRoutes(int fromId, int toId) {
        List<RouteResult> results = new ArrayList<>();
        String sql = "SELECT t.id, t.name, t.total_seats, rs1.departure_time, rs2.arrival_time FROM trains t " +
                "JOIN route_stations rs1 ON t.route_id = rs1.route_id " +
                "JOIN route_stations rs2 ON t.route_id = rs2.route_id " +
                "WHERE rs1.station_id = ? AND rs2.station_id = ? AND rs1.stop_order < rs2.stop_order";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fromId);
            ps.setInt(2, toId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new RouteResult(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getInt("total_seats"),
                        "Direct"
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return results;
    }
    public List<RouteResult> searchIndirectRoutes(int fromId, int toId) {
        List<RouteResult> results = new ArrayList<>();
        String sql = "SELECT t1.id as id1, t1.name as name1, t1.total_seats as seats1, " +
                "t2.id as id2, t2.name as name2, t2.total_seats as seats2, " +
                "s.name as junction_station, " +
                "rs1_dep.departure_time as start_time, " +
                "rs2_arr.arrival_time as end_time " +
                "FROM trains t1 " +
                "JOIN route_stations rs1_dep ON t1.route_id = rs1_dep.route_id " +
                "JOIN route_stations rs1_arr ON t1.route_id = rs1_arr.route_id " +
                "JOIN route_stations rs2_dep ON rs2_dep.station_id = rs1_arr.station_id " +
                "JOIN trains t2 ON rs2_dep.route_id = t2.route_id " +
                "JOIN route_stations rs2_arr ON t2.route_id = rs2_arr.route_id " +
                "JOIN stations s ON rs1_arr.station_id = s.id " +
                "WHERE rs1_dep.station_id = ? AND rs2_arr.station_id = ? " +
                "AND rs1_arr.arrival_time < rs2_dep.departure_time " +
                "AND t1.id <> t2.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fromId);
            ps.setInt(2, toId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String desc = "Change in " + rs.getString("junction_station") +
                        " (" + rs.getString("name1") + " -> " + rs.getString("name2") + ")";

                results.add(new RouteResult(
                        rs.getInt("id1"),
                        desc,
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getInt("seats1"),
                        "Indirect"
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return results;
    }

    public int addRoute(String name) throws SQLException {
        String sql = "INSERT INTO routes (name) VALUES (?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : -1;
        }
    }
}
