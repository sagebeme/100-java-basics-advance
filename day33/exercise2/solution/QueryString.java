import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryString {

    public static String withQuery(String base, Map<String, String> params) {
        if (params.isEmpty()) return base;
        StringBuilder url = new StringBuilder(base).append('?');
        boolean first = true;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (!first) url.append('&');
            url.append(encode(param.getKey())).append('=').append(encode(param.getValue()));
            first = false;
        }
        return url.toString();
    }

    private static String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("q", "nyama choma & chips");
        params.put("timezone", "Africa/Nairobi");
        System.out.println(withQuery("https://example.com/search", params));
    }
}
