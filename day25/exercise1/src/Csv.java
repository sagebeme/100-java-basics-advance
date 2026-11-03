import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Csv {

    // Splits one line into fields. A field in double quotes may contain the delimiter,
    // and "" inside quotes stands for one ".
    public static List<String> parseLine(String line, char delimiter) {
        // TODO: walk the line one character at a time, remembering whether you're inside quotes
        return new ArrayList<>();
    }

    // Every row of a file, skipping the header row if there is one, and blank lines.
    public static List<List<String>> readRows(Path file, char delimiter, boolean hasHeader) throws IOException {
        // TODO
        return new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "day25/exercise1/src/deliveries.csv");
        for (List<String> row : readRows(file, ',', true)) {
            System.out.println(row);
        }
    }
}
