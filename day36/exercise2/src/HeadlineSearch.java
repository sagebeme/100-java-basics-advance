import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HeadlineSearch {

    public static boolean mentions(String title, String word) {
        // TODO: whole word, any capitals
        return false;
    }

    public static List<String> about(List<String> titles, List<String> words, int limit) {
        // TODO
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(about(List.of("KCB Group falls", "KCBX launches", "Safaricom and kcb lead"), List.of("KCB"), 5));
    }
}
