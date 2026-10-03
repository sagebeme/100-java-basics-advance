import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HeadlineSearch {

    public static boolean mentions(String title, String word) {
        Pattern whole = Pattern.compile("(?<!\\w)" + Pattern.quote(word) + "(?!\\w)", Pattern.CASE_INSENSITIVE);
        return whole.matcher(title).find();
    }

    public static List<String> about(List<String> titles, List<String> words, int limit) {
        List<String> found = new ArrayList<>();
        for (String title : titles) {
            if (found.size() == limit) break;
            for (String word : words) {
                if (mentions(title, word)) {
                    found.add(title);
                    break; // one match is enough; don't add the same title twice
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        System.out.println(about(List.of("KCB Group falls", "KCBX launches", "Safaricom and kcb lead"), List.of("KCB"), 5));
    }
}
