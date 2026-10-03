import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TriviaSource {

    public static URI uri(int amount, int category) {
        return URI.create("https://opentdb.com/api.php?amount=" + amount + "&category=" + category + "&type=boolean");
    }

    public static List<Question> parse(String json) {
        List<Question> questions = new ArrayList<>();
        // TODO: throw IllegalStateException unless response_code is 0
        // TODO: one Question per result, with the text and category decoded by Html.decode
        return questions;
    }

    public static List<Question> bundled() {
        // TODO: read /questions.json from the classpath and parse it
        return List.of();
    }

    public static List<Question> load() {
        // TODO: try the API (5-second timeouts); on any failure, return bundled()
        return bundled();
    }
}
