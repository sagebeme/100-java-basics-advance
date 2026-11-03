import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShopLog {

    // Lines with something on them. Blank lines don't count.
    public static int lineCount(Path file) throws IOException {
        // TODO: Files.readAllLines(file)
        return -1;
    }

    public static int wordCount(Path file) throws IOException {
        // TODO
        return -1;
    }

    // Every line mentioning the word, any case, as "3: SALE 120 sugar" with its line number (from 1).
    public static List<String> find(Path file, String word) throws IOException {
        // TODO
        return new ArrayList<>();
    }

    // Adds up every "SALE <shillings> <item>" line. Other lines are ignored.
    public static int totalSales(Path file) throws IOException {
        // TODO
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Path log = Path.of(args.length > 0 ? args[0] : "day24/exercise1/src/duka.log");
        System.out.println(lineCount(log) + " lines, " + wordCount(log) + " words");
        System.out.println("Sugar: " + find(log, "sugar"));
        System.out.println("Sales: KES " + totalSales(log));
    }
}
