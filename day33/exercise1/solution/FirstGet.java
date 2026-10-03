import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FirstGet {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static String fetchText(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("HTTP " + response.statusCode());
        }
        return response.body();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(fetchText(URI.create("https://api.open-meteo.com/v1/forecast?latitude=-1.29&longitude=36.82&current=temperature_2m")));
    }
}
