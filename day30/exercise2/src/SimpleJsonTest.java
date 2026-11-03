import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleJsonTest {

    private static Map<String, String> login() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("site", "kplc.co.ke");
        data.put("user", "cafe-eldoret");
        return data;
    }

    @Test
    void writesAFlatObjectInOrder() {
        assertEquals("{\"site\":\"kplc.co.ke\",\"user\":\"cafe-eldoret\"}", SimpleJson.write(login()));
        assertEquals("{}", SimpleJson.write(new LinkedHashMap<>()));
    }

    @Test
    void escapesQuotesBackslashesAndNewLines() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("note", "Shop 4, \"Eldoret\"\nC:\\vault\tok");
        assertEquals("{\"note\":\"Shop 4, \\\"Eldoret\\\"\\nC:\\\\vault\\tok\"}", SimpleJson.write(data));
    }

    @Test
    void readsWhatItWrites() {
        Map<String, String> data = login();
        data.put("note", "Shop 4, \"Eldoret\": {upstairs}\nC:\\vault");
        data.put("empty", "");
        Map<String, String> back = SimpleJson.read(SimpleJson.write(data));
        assertEquals(data, back);
        assertEquals(List.of("site", "user", "note", "empty"), List.copyOf(back.keySet()));
    }

    @Test
    void readsJsonWithSpacesAndNewLines() {
        String json = "  {\n  \"site\" : \"kplc.co.ke\" ,\n  \"user\":\"cafe-eldoret\"\n}\n";
        assertEquals(login(), SimpleJson.read(json));
        assertEquals(Map.of(), SimpleJson.read(" { } "));
    }

    @Test
    void readsAForwardSlashEscape() {
        assertEquals(Map.of("url", "https://kplc.co.ke"), SimpleJson.read("{\"url\":\"https:\\/\\/kplc.co.ke\"}"));
    }

    @Test
    void saysWhereTheTextWentWrong() {
        JsonException e = assertThrows(JsonException.class, () -> SimpleJson.read("{\"site\" \"kplc\"}"));
        assertEquals("Expected ':' at position 8", e.getMessage());
    }

    @Test
    void refusesBrokenJson() {
        for (String broken : List.of(
                "", "site", "{", "{\"site\":\"kplc\"", "{\"site\":\"kplc}", "{\"site\":kplc}",
                "{\"site\":\"kplc\",}", "{\"site\":\"kplc\"} extra", "{\"a\":\"\\q\"}", "{site:\"kplc\"}")) {
            assertThrows(JsonException.class, () -> SimpleJson.read(broken), broken);
        }
    }
}
