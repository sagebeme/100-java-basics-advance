import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A tiny hand-rolled JSON reader/writer for flat string-to-string objects, so this exercise
 * doesn't depend on a JSON library. Nested structures are out of scope for this version.
 */
public class SimpleJson {

    public static String toJson(Map<String, String> data) {
        StringBuilder json = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!first) json.append(",");
            json.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            first = false;
        }
        return json.append("}").toString();
    }

    public static Map<String, String> parseJson(String json) {
        Map<String, String> result = new LinkedHashMap<>();
        String trimmed = json.trim().replaceAll("^\\{|}$", "");
        if (trimmed.isBlank()) return result;
        for (String pair : trimmed.split(",")) {
            String[] parts = pair.split(":", 2);
            String key = parts[0].trim().replaceAll("^\"|\"$", "");
            String value = parts[1].trim().replaceAll("^\"|\"$", "");
            result.put(key, value);
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("name", "Amina");
        data.put("city", "Nairobi");

        String json = toJson(data);
        System.out.println(json);
        System.out.println(parseJson(json));
    }
}
