import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaskingTest {

    @Test
    void showsOnlyTheLastFourCharacters() {
        assertEquals("••••••••••••4b8e", Masking.mask("atsk_9f2c1e7a55d04b8e"));
    }

    @Test
    void hidesShortSecretsCompletely() {
        assertEquals("••••••••", Masking.mask("abc123"));
        assertEquals("••••••••", Masking.mask("12345678"));
    }

    @Test
    void saysWhenThereIsNoSecret() {
        assertEquals("(not set)", Masking.mask(null));
        assertEquals("(not set)", Masking.mask(""));
    }

    @Test
    void redactsEveryCopyOfEverySecret() {
        String line = "key=atsk_9f2c1e7a55d04b8e pass=hunter2 again=atsk_9f2c1e7a55d04b8e";
        assertEquals("key=••••••••••••4b8e pass=•••••••• again=••••••••••••4b8e",
                Masking.redact(line, List.of("atsk_9f2c1e7a55d04b8e", "hunter2")));
    }

    @Test
    void ignoresEmptySecrets() {
        assertEquals("nothing secret", Masking.redact("nothing secret", Arrays.asList("", null)));
    }
}
