import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstGetTest {

    private HttpServer server;
    private String base;

    @BeforeEach
    void startFakeServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/hello", exchange -> {
            byte[] body = "{\"greeting\":\"Habari\"}".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.createContext("/missing", exchange -> {
            exchange.sendResponseHeaders(404, -1);
            exchange.close();
        });
        server.start();
        base = "http://127.0.0.1:" + server.getAddress().getPort();
    }

    @AfterEach
    void stopFakeServer() {
        server.stop(0);
    }

    @Test
    void returnsTheBodyOfASuccessfulResponse() throws Exception {
        assertEquals("{\"greeting\":\"Habari\"}", FirstGet.fetchText(URI.create(base + "/hello")));
    }

    @Test
    void aMissingPageIsAnErrorWithItsStatus() {
        IOException e = assertThrows(IOException.class, () -> FirstGet.fetchText(URI.create(base + "/missing")));
        assertTrue(e.getMessage().contains("404"));
    }
}
