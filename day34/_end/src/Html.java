import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Trivia APIs send text with HTML entities: &quot; for ", &#039; for '. This turns them back into characters.
public class Html {

    private static final Map<String, String> NAMED = Map.of(
            "quot", "\"", "amp", "&", "lt", "<", "gt", ">", "apos", "'", "nbsp", " ",
            "eacute", "é", "rsquo", "’", "lsquo", "‘", "hellip", "…");
    private static final Pattern ENTITY = Pattern.compile("&(#x[0-9a-fA-F]+|#[0-9]+|[a-zA-Z]+);");

    public static String decode(String text) {
        Matcher m = ENTITY.matcher(text);
        StringBuilder out = new StringBuilder();
        while (m.find()) {
            String name = m.group(1);
            String replacement;
            if (name.startsWith("#x")) {
                replacement = Character.toString(Integer.parseInt(name.substring(2), 16));
            } else if (name.startsWith("#")) {
                replacement = Character.toString(Integer.parseInt(name.substring(1)));
            } else {
                replacement = NAMED.getOrDefault(name, m.group()); // leave unknown entities as they were
            }
            m.appendReplacement(out, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(out);
        return out.toString();
    }
}
