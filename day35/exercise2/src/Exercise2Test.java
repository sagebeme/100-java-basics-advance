import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2Test {

    @Test
    void savingThenLoadingKeepsTheSameValues() throws IOException {
        String path = Files.createTempFile("app", ".properties").toString();
        Properties props = new Properties();
        props.setProperty("app.name", "MyApp");

        Exercise2.save(path, props);
        Properties loaded = Exercise2.load(path);

        assertEquals("MyApp", loaded.getProperty("app.name"));
    }

    @Test
    void missingKeysFallBackToTheDefault() {
        Properties props = new Properties();
        assertEquals("Unknown", Exercise2.getOrDefault(props, "app.author", "Unknown"));
    }

    @Test
    void presentKeysWinOverTheDefault() {
        Properties props = new Properties();
        props.setProperty("app.name", "MyApp");
        assertEquals("MyApp", Exercise2.getOrDefault(props, "app.name", "Unknown"));
    }
}
