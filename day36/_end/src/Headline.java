import java.time.Instant;
import java.util.regex.Pattern;

public record Headline(String title, String source, Instant published) {

    // True when the title contains the word, whatever its capitals, and not just as part of a longer word.
    // (?<!\w) and (?!\w) mean "no letter, digit or _ just before / just after", which also works for "E.A.".
    public boolean mentions(String word) {
        return Pattern.compile("(?<!\\w)" + Pattern.quote(word) + "(?!\\w)", Pattern.CASE_INSENSITIVE).matcher(title).find();
    }
}
