import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmsAlertsTest {

    private static Properties file(String... pairs) {
        Properties p = new Properties();
        for (int i = 0; i < pairs.length; i += 2) p.setProperty(pairs[i], pairs[i + 1]);
        return p;
    }

    @Test
    void theEnvironmentBeatsTheFileAndTheFileBeatsTheDefault() {
        AppConfig config = AppConfig.load(
                Map.of("SMS_SENDER", "FROMENV"),
                file("sms.sender", "FROMFILE", "sms.username", "ruaka-bakery"));
        assertEquals("FROMENV", config.get("sms.sender"));
        assertEquals("ruaka-bakery", config.get("sms.username"));
        assertTrue(config.getBoolean("sms.sandbox")); // only the default had it
    }

    @Test
    void aBlankEnvironmentVariableDoesNotHideTheFile() {
        AppConfig config = AppConfig.load(Map.of("SMS_USERNAME", "  "), file("sms.username", "ruaka-bakery"));
        assertEquals("ruaka-bakery", config.get("sms.username"));
    }

    @Test
    void reportsEveryMissingSettingAtOnce() {
        ConfigException e = assertThrows(ConfigException.class,
                () -> AppConfig.load(Map.of(), file()).require("sms.username", "sms.apiKey"));
        assertTrue(e.getMessage().contains("sms.username"));
        assertTrue(e.getMessage().contains("sms.apiKey"));
        assertTrue(e.getMessage().contains("SMS_API_KEY"));
    }

    @Test
    void neverPrintsTheWholeKey() {
        String key = "atsk_9f2c1e7a55d04b8e";
        String shown = AppConfig.load(Map.of("SMS_API_KEY", key), file("sms.username", "x")).describe();
        assertFalse(shown.contains(key));
        assertTrue(shown.contains("sms.apiKey=••••••••••••4b8e"));
    }

    @Test
    void masksShortSecretsCompletely() {
        assertEquals("••••••••", Secrets.mask("abc123"));
        assertEquals("(not set)", Secrets.mask(null));
    }

    @Test
    void normalisesKenyanNumbers() {
        for (String raw : new String[]{"0712345678", "712345678", "254712345678", "+254 712 345 678", "0712-345-678"}) {
            assertEquals("+254712345678", Phone.normalise(raw), raw);
        }
        assertEquals("+254110123456", Phone.normalise("0110123456"));
    }

    @Test
    void refusesNumbersThatArentKenyanMobiles() {
        assertThrows(IllegalArgumentException.class, () -> Phone.normalise("0201234567"));
        assertThrows(IllegalArgumentException.class, () -> Phone.normalise("07123"));
        assertThrows(IllegalArgumentException.class, () -> Phone.normalise("+1 555 123 4567"));
    }
}
