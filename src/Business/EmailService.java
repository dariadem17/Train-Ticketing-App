package Business;

public class EmailService {
    private final EmailProvider provider;

    public EmailService() {
        this.provider = new MockEmailProvider();
    }

    public void sendConfirmation(String to, String trainName, int seats) {
        if (!isValidEmail(to)) return;

        String body = "Booking confirmation: " + seats + " seats for train " + trainName;
        provider.sendEmail(to, "Your ticket", body);
    }

    public void notifyDelay(String customerEmail, String trainName, int delayMinutes) {
        if (!isValidEmail(customerEmail)) return;

        String body = "<html><body>" +
                "<h1>Notification for delay</h1>" +
                "<p>Train <b>" + trainName + "</b> has a delay of <b>" + delayMinutes + " minutes</b>.</p>" +
                "</body></html>";

        provider.sendEmail(customerEmail, "Update : " + trainName, body);
    }

    public boolean isValidEmail(String email) {
        if (email == null) return false;
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}