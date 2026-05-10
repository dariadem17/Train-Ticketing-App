package Model;

public class Booking {
    private int id;
    private int trainId;
    private String customerEmail;
    private int seatsBooked;

    public Booking(int trainId, int seatsBooked, String customerEmail) {
        this.trainId = trainId;
        this.seatsBooked = seatsBooked;
        this.customerEmail = customerEmail;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
}
