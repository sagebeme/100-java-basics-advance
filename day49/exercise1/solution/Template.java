import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {

    private static final Pattern PLACEHOLDER = Pattern.compile("\\{\\{(\\w+)}}");

    public static String fill(String template, Map<String, String> values) {
        Matcher m = PLACEHOLDER.matcher(template);
        StringBuilder out = new StringBuilder();
        Set<String> missing = new LinkedHashSet<>();
        while (m.find()) {
            String value = values.get(m.group(1));
            if (value == null) {
                missing.add(m.group(1));
                value = m.group();
            }
            m.appendReplacement(out, Matcher.quoteReplacement(value));
        }
        m.appendTail(out);
        if (!missing.isEmpty()) {
            throw new IllegalArgumentException("Missing values for: " + String.join(", ", missing));
        }
        return out.toString();
    }
}
