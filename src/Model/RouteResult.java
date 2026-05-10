package Model;

public class RouteResult {
    private int trainId;
    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private String type;

    public RouteResult(int trainId, String trainName, String departureTime, String arrivalTime, int totalSeats, String type) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.type = type;
    }

    public int getTrainId() {
        return trainId;
    }
    public String getTrainName() {
        return trainName;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public String getType() {
        return type;
    }
}