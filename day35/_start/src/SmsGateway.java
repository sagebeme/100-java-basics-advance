// Anything that can send a text. A real one would call an SMS provider's API with the username and key.
public interface SmsGateway {
    void send(String to, String message);
}
