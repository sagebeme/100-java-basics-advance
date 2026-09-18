import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void fallsBackToTheDefaultWhenTheVariableIsNotSet() {
        assertEquals("MyApp", Exercise1.getEnvOrDefault("A_VAR_THAT_DOES_NOT_EXIST_XYZ", "MyApp"));
    }

    @Test
    void fallsBackToTheDefaultIntWhenMissing() {
        assertEquals(8080, Exercise1.getEnvIntOrDefault("A_VAR_THAT_DOES_NOT_EXIST_XYZ", 8080));
    }

    @Test
    void picksUpARealEnvironmentVariableWhenOneExists() {
        // PATH exists on both Windows and Unix-like systems in practice.
        String path = System.getenv("PATH") != null ? System.getenv("PATH") : System.getenv("Path");
        if (path != null) {
            assertEquals(path, Exercise1.getEnvOrDefault(System.getenv("PATH") != null ? "PATH" : "Path", "fallback"));
        }
    }
}
