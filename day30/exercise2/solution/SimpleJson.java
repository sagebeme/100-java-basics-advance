import java.util.LinkedHashMap;
import java.util.Map;

// Reads and writes flat JSON objects whose values are all strings: {"site":"kplc.co.ke","user":"cafe"}.
public class SimpleJson {

    public static String write(Map<String, String> data) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (json.length() > 1) {
                json.append(',');
            }
            json.append(quote(entry.getKey())).append(':').append(quote(entry.getValue()));
        }
        return json.append('}').toString();
    }

    static String quote(String text) {
        StringBuilder out = new StringBuilder("\"");
        for (char c : text.toCharArray()) {
            switch (c) {
                case '"':
                    out.append("\\\"");
                    break;
                case '\\':
                    out.append("\\\\");
                    break;
                case '\n':
                    out.append("\\n");
                    break;
                case '\t':
                    out.append("\\t");
                    break;
                default:
                    out.append(c);
            }
        }
        return out.append('"').toString();
    }

    public static Map<String, String> read(String json) {
        return new Reader(json).object();
    }

    // Walks the text one character at a time. `pos` is always the next character to look at.
    private static class Reader {

        private final String text;
        private int pos;

        Reader(String text) {
            this.text = text;
        }

        Map<String, String> object() {
            Map<String, String> result = new LinkedHashMap<>();
            skipSpaces();
            expect('{');
            skipSpaces();
            if (peek() == '}') {
                pos++;
            } else {
                while (true) {
                    skipSpaces();
                    String key = string();
                    skipSpaces();
                    expect(':');
                    skipSpaces();
                    result.put(key, string());
                    skipSpaces();
                    if (peek() == ',') {
                        pos++;
                    } else {
                        expect('}');
                        break;
                    }
                }
            }
            skipSpaces();
            if (pos < text.length()) {
                throw new JsonException("Unexpected text after the object", pos);
            }
            return result;
        }

        private String string() {
            expect('"');
            StringBuilder out = new StringBuilder();
            while (true) {
                if (pos >= text.length()) {
                    throw new JsonException("Unfinished string", pos);
                }
                char c = text.charAt(pos++);
                if (c == '"') {
                    return out.toString();
                }
                if (c != '\\') {
                    out.append(c);
                    continue;
                }
                if (pos >= text.length()) {
                    throw new JsonException("Unfinished string", pos);
                }
                char escaped = text.charAt(pos++);
                switch (escaped) {
                    case '"':
                    case '\\':
                    case '/':
                        out.append(escaped);
                        break;
                    case 'n':
                        out.append('\n');
                        break;
                    case 't':
                        out.append('\t');
                        break;
                    default:
                        throw new JsonException("Unknown escape \\" + escaped, pos - 2);
                }
            }
        }

        private char peek() {
            if (pos >= text.length()) {
                throw new JsonException("Unexpected end", pos);
            }
            return text.charAt(pos);
        }

        private void expect(char wanted) {
            if (peek() != wanted) {
                throw new JsonException("Expected '" + wanted + "'", pos);
            }
            pos++;
        }

        private void skipSpaces() {
            while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) {
                pos++;
            }
        }
    }

    public static void main(String[] args) {
        Map<String, String> login = new LinkedHashMap<>();
        login.put("site", "kplc.co.ke");
        login.put("note", "Shop 4, \"Eldoret\" branch");
        String json = write(login);
        System.out.println(json);
        System.out.println(read(json));
    }
}
