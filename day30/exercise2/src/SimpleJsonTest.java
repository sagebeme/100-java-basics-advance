import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleJsonTest {

    @Test
    void convertsAMapToAJsonObject() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("name", "Amina");
        data.put("city", "Nairobi");

        assertEquals("{\"name\":\"Amina\",\"city\":\"Nairobi\"}", SimpleJson.toJson(data));
    }

    @Test
    void parsingReversesToJson() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("name", "Amina");
        data.put("city", "Nairobi");

        assertEquals(data, SimpleJson.parseJson(SimpleJson.toJson(data)));
    }

    @Test
    void anEmptyObjectParsesToAnEmptyMap() {
        assertEquals(0, SimpleJson.parseJson("{}").size());
    }
}
