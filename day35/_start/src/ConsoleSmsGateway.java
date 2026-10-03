// Prints instead of sending, so you can run the program without an account or credit.
public class ConsoleSmsGateway implements SmsGateway {
    @Override
    public void send(String to, String message) {
        System.out.println("Sending to " + to + ": " + message);
        System.out.println("Sent (console gateway, nothing left the computer).");
    }
}
