import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FirstGet {

    public static String fetchText(URI uri) throws IOException, InterruptedException {
        // TODO: GET the uri; return the body for 200, throw IOException("HTTP " + status) otherwise
        return "";
    }

    public static void main(String[] args) throws Exception {
        System.out.println(fetchText(URI.create("https://api.open-meteo.com/v1/forecast?latitude=-1.29&longitude=36.82&current=temperature_2m")));
    }
}
