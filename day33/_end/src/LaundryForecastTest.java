import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaundryForecastTest {

    private HttpServer server;

    private static String fixture(String name) throws IOException {
        try (InputStream in = LaundryForecastTest.class.getResourceAsStream("/" + name)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    // A fake weather service on a free local port: answers every request with the given status and body.
    private URI fakeService(int status, String body) throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(status, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.close();
        });
        server.start();
        return URI.create("http://127.0.0.1:" + server.getAddress().getPort() + "/v1/forecast");
    }

    @AfterEach
    void stopServer() {
        if (server != null) server.stop(0);
    }

    @Test
    void buildsAnEncodedOpenMeteoUrl() {
        String url = LaundryForecast.forecastUri(-1.2864, 36.8172).toString();
        assertTrue(url.startsWith("https://api.open-meteo.com/v1/forecast?latitude=-1.2864&longitude=36.8172"));
        assertTrue(url.contains("hourly=precipitation_probability"));
        assertTrue(url.contains("timezone=Africa%2FNairobi"));
    }

    @Test
    void readsEveryHourOfTheForecast() throws IOException {
        List<HourlyRain> hours = LaundryForecast.parse(fixture("forecast-wet.json"));
        assertEquals(24, hours.size());
        assertEquals(13, hours.get(13).time().getHour());
        assertEquals(80, hours.get(13).chance());
    }

    @Test
    void aDryDayMeansHangItOut() throws IOException {
        assertEquals("Hang it out.", LaundryForecast.verdict(LaundryForecast.parse(fixture("forecast-dry.json")), 8, 17));
    }

    @Test
    void aWetAfternoonMeansKeepItInside() throws IOException {
        assertEquals("Keep it inside.", LaundryForecast.verdict(LaundryForecast.parse(fixture("forecast-wet.json")), 8, 17));
    }

    @Test
    void onlyTheChosenHoursCount() throws IOException {
        List<HourlyRain> wet = LaundryForecast.parse(fixture("forecast-wet.json"));
        assertEquals("Risky: hang it near the door.", LaundryForecast.verdict(wet, 9, 11)); // 35% and 45%
        assertEquals("Hang it out.", LaundryForecast.verdict(wet, 0, 8));
    }

    @Test
    void fetchesTheBodyFromAWorkingService() throws Exception {
        URI uri = fakeService(200, fixture("forecast-dry.json"));
        assertEquals(24, LaundryForecast.parse(new WeatherClient().fetch(uri)).size());
    }

    @Test
    void aFailedResponseIsAnErrorNotData() throws Exception {
        URI uri = fakeService(500, "{\"error\":true,\"reason\":\"Something broke\"}");
        ApiException e = assertThrows(ApiException.class, () -> new WeatherClient().fetch(uri));
        assertEquals(500, e.status());
    }
}
