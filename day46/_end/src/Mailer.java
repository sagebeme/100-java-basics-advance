// Anything that can send an email. ChamaReminders only knows this interface, so a test can pass a
// fake and a real program can pass an SMTP sender without changing the reminder logic.
public interface Mailer {
    void send(Email email);
}
