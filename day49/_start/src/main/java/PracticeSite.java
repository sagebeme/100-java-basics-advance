import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;

// Serves the practice website in src/main/resources/site on a free port of your own computer.
// Bots in this course only ever automate sites like this one, never real accounts on real services.
public class PracticeSite implements AutoCloseable {

    private final HttpServer server;

    public PracticeSite() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/")) path = "/index.html";
            try (InputStream in = PracticeSite.class.getResourceAsStream("/site" + path)) {
                if (in == null || path.contains("..")) {
                    exchange.sendResponseHeaders(404, -1);
                } else {
                    byte[] body = in.readAllBytes();
                    exchange.getResponseHeaders().add("Content-Type", contentType(path));
                    exchange.sendResponseHeaders(200, body.length);
                    exchange.getResponseBody().write(body);
                }
            }
            exchange.close();
        });
        server.start();
    }

    static String contentType(String path) {
        if (path.endsWith(".html")) return "text/html; charset=utf-8";
        if (path.endsWith(".css")) return "text/css; charset=utf-8";
        if (path.endsWith(".js")) return "text/javascript; charset=utf-8";
        if (path.endsWith(".json")) return "application/json; charset=utf-8";
        return "application/octet-stream";
    }

    public URI url(String path) {
        return URI.create("http://127.0.0.1:" + server.getAddress().getPort() + path);
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
