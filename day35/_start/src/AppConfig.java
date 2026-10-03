import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AppConfig {

    static final Map<String, String> ENV_NAMES = Map.of(
            "sms.username", "SMS_USERNAME",
            "sms.apiKey", "SMS_API_KEY",
            "sms.sender", "SMS_SENDER",
            "sms.sandbox", "SMS_SANDBOX");

    static final Map<String, String> DEFAULTS = Map.of(
            "sms.sender", "SHOP",
            "sms.sandbox", "true");

    static final List<String> SECRET_KEYS = List.of("sms.apiKey");

    private final Map<String, String> values;

    private AppConfig(Map<String, String> values) {
        this.values = values;
    }

    public static AppConfig load(Map<String, String> env, Properties file) {
        Map<String, String> values = new LinkedHashMap<>();
        // TODO: for each setting: a non-blank environment variable, else a non-blank file value, else the default
        return new AppConfig(values);
    }

    public static Properties readFile(Path path) throws IOException {
        Properties props = new Properties();
        // TODO: load the file if it exists
        return props;
    }

    public AppConfig require(String... keys) {
        // TODO: throw one ConfigException naming every missing key and its environment variable
        return this;
    }

    public String get(String key) {
        return values.get(key);
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(values.get(key));
    }

    public String describe() {
        // TODO: "key=value, key=value", with secret values passed through Secrets.mask
        return "";
    }
}
