import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WeatherClient {

    private final HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

    public String fetch(URI uri) throws IOException, InterruptedException {
        // TODO: build a GET request with a 10-second timeout and send it
        // TODO: throw new ApiException(status, ...) unless the status is 200
        return "";
    }
}
