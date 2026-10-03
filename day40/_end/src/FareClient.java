import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// Talks to a fare API over HTTP: GET {base}/fares?from=NBO&to=MBA&depart=2026-12-25&return=2026-12-27
public class FareClient {

    private final String base;
    private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

    public FareClient(String base) {
        this.base = base.endsWith("/") ? base.substring(0, base.length() - 1) : base;
    }

    public List<Fare> search(String from, String to, LocalDate depart, LocalDate back) throws IOException, InterruptedException {
        URI uri = URI.create(base + "/fares?from=" + from + "&to=" + to + "&depart=" + depart + "&return=" + back);
        HttpResponse<String> response = http.send(HttpRequest.newBuilder(uri).timeout(Duration.ofSeconds(10)).build(),
                HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Fare search for " + to + " failed with HTTP " + response.statusCode());
        }
        return parse(response.body());
    }

    public static List<Fare> parse(String json) {
        JSONArray items = new JSONObject(json).getJSONArray("fares");
        List<Fare> fares = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject f = items.getJSONObject(i);
            fares.add(new Fare(f.getString("to"), f.getString("city"), LocalDate.parse(f.getString("depart")),
                    LocalDate.parse(f.getString("return")), f.getJSONObject("price").getInt("amount"),
                    f.getString("airline"), f.getInt("stops")));
        }
        return fares;
    }
}
