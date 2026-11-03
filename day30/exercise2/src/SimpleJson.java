import java.util.LinkedHashMap;
import java.util.Map;

// Reads and writes flat JSON objects whose values are all strings: {"site":"kplc.co.ke","user":"cafe"}.
public class SimpleJson {

    public static String write(Map<String, String> data) {
        // TODO: {"key":"value","key":"value"} with no spaces, keys in the map's order
        return "";
    }

    // "Shop 4, "Eldoret"" → "\"Shop 4, \\\"Eldoret\\\"\"": wrap in quotes and escape " \ newline tab.
    static String quote(String text) {
        // TODO
        return "";
    }

    public static Map<String, String> read(String json) {
        // TODO: walk the text one character at a time; see the README for the plan.
        //       Anything that isn't valid → throw new JsonException("Expected ':'", position)
        return new LinkedHashMap<>();
    }

    public static void main(String[] args) {
        Map<String, String> login = new LinkedHashMap<>();
        login.put("site", "kplc.co.ke");
        login.put("note", "Shop 4, \"Eldoret\" branch");
        String json = write(login);
        System.out.println(json);
        System.out.println(read(json));
    }
}
