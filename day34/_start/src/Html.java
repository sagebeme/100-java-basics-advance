import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Trivia APIs send text with HTML entities: &quot; for ", &#039; for '. This turns them back into characters.
public class Html {

    public static String decode(String text) {
        // TODO: replace &quot; &amp; &lt; &gt; &apos;, plus numeric ones like &#039; and &#xe9;
        // TODO: leave entities you don't recognise exactly as they were
        return text;
    }
}
