import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

// A pretend listings site on your own computer: /listings?page=1..3 and a /robots.txt.
// It counts the requests it gets, so tests can check the scraper doesn't fetch a page twice.
public class PracticeShop implements AutoCloseable {

    static final String ROBOTS = """
            # Gikomba Online
            User-agent: *
            Disallow: /admin
            Disallow: /checkout
            """;

    private final HttpServer server;
    private final AtomicInteger requests = new AtomicInteger();

    public PracticeShop(Path folder) throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            requests.incrementAndGet();
            String path = exchange.getRequestURI().getPath();
            String query = exchange.getRequestURI().getQuery();
            String body = null;
            if (path.equals("/robots.txt")) {
                body = ROBOTS;
            } else if (path.equals("/listings")) {
                String page = query != null && query.startsWith("page=") ? query.substring(5) : "1";
                Path file = folder.resolve("listings-" + page + ".html");
                if (page.matches("\\d+") && Files.exists(file)) body = Files.readString(file);
            }
            byte[] bytes = (body == null ? "Not found" : body).getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(body == null ? 404 : 200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.close();
        });
        server.start();
    }

    public URI base() {
        return URI.create("http://127.0.0.1:" + server.getAddress().getPort());
    }

    public int requests() {
        return requests.get();
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
