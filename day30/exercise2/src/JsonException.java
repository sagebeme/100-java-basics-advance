// Thrown when the text isn't the JSON SimpleJson understands. Already written.
public class JsonException extends RuntimeException {

    public JsonException(String message, int position) {
        super(message + " at position " + position);
    }
}
