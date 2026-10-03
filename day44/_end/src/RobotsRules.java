import java.util.ArrayList;
import java.util.List;

// The "User-agent: *" rules from a robots.txt file: which paths robots are asked to stay out of.
public class RobotsRules {

    private final List<String> disallowed = new ArrayList<>();

    public RobotsRules(String robotsTxt) {
        boolean forEveryone = false;
        for (String raw : robotsTxt.split("\n")) {
            String line = raw.replaceAll("#.*", "").trim(); // drop comments
            if (line.isEmpty()) continue;
            String[] parts = line.split(":", 2);
            if (parts.length < 2) continue;
            String field = parts[0].trim().toLowerCase();
            String value = parts[1].trim();
            if (field.equals("user-agent")) {
                forEveryone = value.equals("*");
            } else if (field.equals("disallow") && forEveryone && !value.isEmpty()) {
                disallowed.add(value);
            }
        }
    }

    public boolean isAllowed(String path) {
        for (String prefix : disallowed) {
            if (path.startsWith(prefix)) return false;
        }
        return true;
    }
}
