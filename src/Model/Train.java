package Model;

public class Train {
    private int id;
    private String name;
    private int routeId;
    private int totalSeats;
    private int delayMinutes;

    public Train(int id, String name, int totalSeats, int routeId, int delayMinutes) {
        this.id = id;
        this.name = name;
        this.totalSeats = totalSeats;
        this.routeId = routeId;
        this.delayMinutes = delayMinutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(int delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    @Override
    public String toString() {
        return this.name + " (Delay: " + this.delayMinutes + " min)";
    }
}
