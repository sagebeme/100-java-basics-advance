import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

// A pretend fare API on your own computer, so the project works without an account at a real one.
// It answers GET /fares?from=..&to=..&depart=..&return=.. from the fares in fares.json.
public class PracticeFareServer implements AutoCloseable {

    private final HttpServer server;

    public PracticeFareServer(Path faresFile) throws IOException {
        JSONArray all = new JSONObject(Files.readString(faresFile)).getJSONArray("fares");
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0); // port 0: any free port
        server.createContext("/fares", exchange -> {
            Map<String, String> q = query(exchange.getRequestURI().getRawQuery());
            JSONArray matches = new JSONArray();
            for (int i = 0; i < all.length(); i++) {
                JSONObject f = all.getJSONObject(i);
                if (f.getString("from").equals(q.get("from")) && f.getString("to").equals(q.get("to"))
                        && f.getString("depart").equals(q.get("depart")) && f.getString("return").equals(q.get("return"))) {
                    matches.put(f);
                }
            }
            byte[] body = new JSONObject().put("fares", matches).toString().getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.start();
    }

    public URI baseUri() {
        return URI.create("http://127.0.0.1:" + server.getAddress().getPort());
    }

    private static Map<String, String> query(String raw) {
        Map<String, String> params = new HashMap<>();
        if (raw == null) return params;
        for (String pair : raw.split("&")) {
            String[] kv = pair.split("=", 2);
            params.put(kv[0], kv.length > 1 ? kv[1] : "");
        }
        return params;
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
