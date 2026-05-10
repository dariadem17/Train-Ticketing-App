package Business;

public interface EmailProvider {
    void sendEmail(String to, String subject, String body);
}