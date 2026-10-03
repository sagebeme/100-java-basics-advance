import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryString {

    public static String withQuery(String base, Map<String, String> params) {
        // TODO: base + "?" + encoded name=value pairs joined by "&"
        return base;
    }

    public static void main(String[] args) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("q", "nyama choma & chips");
        params.put("timezone", "Africa/Nairobi");
        System.out.println(withQuery("https://example.com/search", params));
    }
}
