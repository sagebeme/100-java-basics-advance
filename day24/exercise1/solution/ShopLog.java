import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShopLog {

    // Lines with something on them. Blank lines don't count.
    public static int lineCount(Path file) throws IOException {
        int count = 0;
        for (String line : Files.readAllLines(file)) {
            if (!line.isBlank()) {
                count++;
            }
        }
        return count;
    }

    public static int wordCount(Path file) throws IOException {
        int count = 0;
        for (String line : Files.readAllLines(file)) {
            if (!line.isBlank()) {
                count += line.strip().split("\\s+").length;
            }
        }
        return count;
    }

    // Every line mentioning the word, any case, as "3: SALE 120 sugar" with its line number (from 1).
    public static List<String> find(Path file, String word) throws IOException {
        List<String> found = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).toLowerCase().contains(word.toLowerCase())) {
                found.add((i + 1) + ": " + lines.get(i));
            }
        }
        return found;
    }

    // Adds up every "SALE <shillings> <item>" line. Other lines are ignored.
    public static int totalSales(Path file) throws IOException {
        int total = 0;
        for (String line : Files.readAllLines(file)) {
            String[] parts = line.strip().split("\\s+");
            if (parts.length >= 2 && parts[0].equals("SALE")) {
                total += Integer.parseInt(parts[1]);
            }
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        Path log = Path.of(args.length > 0 ? args[0] : "day24/exercise1/src/duka.log");
        System.out.println(lineCount(log) + " lines, " + wordCount(log) + " words");
        System.out.println("Sugar: " + find(log, "sugar"));
        System.out.println("Sales: KES " + totalSales(log));
    }
}
