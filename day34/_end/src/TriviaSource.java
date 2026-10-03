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

// Where questions come from: the Open Trivia Database, or the bundled questions.json when offline.
public class TriviaSource {

    public static URI uri(int amount, int category) {
        return URI.create("https://opentdb.com/api.php?amount=" + amount + "&category=" + category + "&type=boolean");
    }

    public static List<Question> parse(String json) {
        JSONObject root = new JSONObject(json);
        int code = root.getInt("response_code");
        if (code != 0) {
            throw new IllegalStateException("The trivia service answered with response_code " + code);
        }
        JSONArray results = root.getJSONArray("results");
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject r = results.getJSONObject(i);
            questions.add(new Question(Html.decode(r.getString("question")),
                    r.getString("correct_answer").equalsIgnoreCase("True"),
                    Html.decode(r.getString("category"))));
        }
        return questions;
    }

    public static List<Question> bundled() {
        try (InputStream in = TriviaSource.class.getResourceAsStream("/questions.json")) {
            return parse(new String(in.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("questions.json is missing", e);
        }
    }

    // Ten geography questions from the internet, or the Kenya questions if that fails for any reason.
    public static List<Question> load() {
        try {
            HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
            HttpRequest request = HttpRequest.newBuilder(uri(10, 22)).timeout(Duration.ofSeconds(5)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return parse(response.body());
            }
        } catch (IOException | RuntimeException e) {
            // fall through to the bundled questions
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return bundled();
    }
}
