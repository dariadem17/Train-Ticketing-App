package Business;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class MockEmailProvider implements EmailProvider {
    @Override
    public void sendEmail(String to, String subject, String body) {
        try (FileWriter fw = new FileWriter("mail_server.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("--------------------------------------");
            pw.println("TIMESTAMP: " + LocalDateTime.now());
            pw.println("TO: " + to);
            pw.println("SUBJECT: " + subject);
            pw.println("BODY: " + body);
            pw.println("--------------------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}