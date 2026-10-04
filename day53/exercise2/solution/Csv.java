import java.util.ArrayList;
import java.util.List;

public class Csv {

    public static String line(List<String> fields) {
        List<String> out = new ArrayList<>();
        for (String f : fields) {
            out.add(f.contains(",") || f.contains("\"") || f.contains("\n") ? "\"" + f.replace("\"", "\"\"") + "\"" : f);
        }
        return String.join(",", out);
    }

    public static List<String> parse(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"' && i + 1 < line.length() && line.charAt(i + 1) == '"') { field.append('"'); i++; }
                else if (c == '"') inQuotes = false;
                else field.append(c);
            } else if (c == '"') {
                inQuotes = true;
            } else if (c == ',') {
                fields.add(field.toString());
                field.setLength(0);
            } else {
                field.append(c);
            }
        }
        fields.add(field.toString());
        return fields;
    }
}
