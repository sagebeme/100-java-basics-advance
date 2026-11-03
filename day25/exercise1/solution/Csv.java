import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Csv {

    // Splits one line into fields. A field in double quotes may contain the delimiter,
    // and "" inside quotes stands for one ".
    public static List<String> parseLine(String line, char delimiter) {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"' && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    field.append('"');
                    i++;
                } else if (c == '"') {
                    inQuotes = false;
                } else {
                    field.append(c);
                }
            } else if (c == '"') {
                inQuotes = true;
            } else if (c == delimiter) {
                fields.add(field.toString());
                field.setLength(0);
            } else {
                field.append(c);
            }
        }
        if (inQuotes) {
            throw new IllegalArgumentException("A quote is never closed in: " + line);
        }
        fields.add(field.toString());
        return fields;
    }

    // Every row of a file, skipping the header row if there is one, and blank lines.
    public static List<List<String>> readRows(Path file, char delimiter, boolean hasHeader) throws IOException {
        List<List<String>> rows = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        for (int i = hasHeader ? 1 : 0; i < lines.size(); i++) {
            if (!lines.get(i).isBlank()) {
                rows.add(parseLine(lines.get(i), delimiter));
            }
        }
        return rows;
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "day25/exercise1/src/deliveries.csv");
        for (List<String> row : readRows(file, ',', true)) {
            System.out.println(row);
        }
    }
}
