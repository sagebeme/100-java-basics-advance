import java.util.ArrayList;
import java.util.List;

public class Exercise1 {

    /**
     * Splits one CSV line on the given delimiter, trimming each field.
     */
    public static List<String> parseLine(String line, String delimiter) {
        List<String> fields = new ArrayList<>();
        for (String field : line.split(delimiter)) {
            fields.add(field.trim());
        }
        return fields;
    }

    /**
     * Parses a whole CSV file's content, skipping the header row.
     */
    public static List<List<String>> parseCsv(String content, String delimiter) {
        List<List<String>> rows = new ArrayList<>();
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            if (lines[i].isBlank()) continue;
            rows.add(parseLine(lines[i], delimiter));
        }
        return rows;
    }

    public static void main(String[] args) {
        String csv = "name,age\nAmina,30\nKip,25";
        System.out.println(parseCsv(csv, ","));
    }
}
