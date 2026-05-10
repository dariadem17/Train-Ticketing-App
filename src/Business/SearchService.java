package Business;

import DAO.RouteDAO;
import Model.RouteResult;
import java.util.List;

public class SearchService {
    private RouteDAO routeDAO = new RouteDAO();

    public List<RouteResult> search(int fromId, int toId) {
        return routeDAO.searchRoutes(fromId, toId);
    }
}