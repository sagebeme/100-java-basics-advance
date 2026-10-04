import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostCheck {

    public static List<String> problems(String text, String lastPost) {
        List<String> problems = new ArrayList<>();
        if (text == null || text.isBlank()) {
            problems.add("Nothing to post.");
            return problems;
        }
        if (text.length() > 280) problems.add("Too long: " + text.length() + " characters (max 280).");
        if (Arrays.stream(text.split("\\s+")).filter(w -> w.startsWith("#")).count() > 3) problems.add("Too many hashtags.");
        String letters = text.replaceAll("[^\\p{L}]", "");
        if (letters.length() >= 10 && letters.equals(letters.toUpperCase())) problems.add("Don't shout.");
        if (lastPost != null && text.trim().equalsIgnoreCase(lastPost.trim())) problems.add("You just posted that.");
        return problems;
    }
}
