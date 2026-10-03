import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

// Settings come from three layers. For each key, the first layer that has it wins:
// the environment variable, then the properties file, then the default.
public class AppConfig {

    // setting → the environment variable that can override it
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
        for (String key : List.of("sms.username", "sms.apiKey", "sms.sender", "sms.sandbox")) {
            String fromEnv = env.get(ENV_NAMES.get(key));
            if (fromEnv != null && !fromEnv.isBlank()) {
                values.put(key, fromEnv.trim());
            } else if (file.getProperty(key) != null && !file.getProperty(key).isBlank()) {
                values.put(key, file.getProperty(key).trim());
            } else if (DEFAULTS.containsKey(key)) {
                values.put(key, DEFAULTS.get(key));
            }
        }
        return new AppConfig(values);
    }

    public static Properties readFile(Path path) throws IOException {
        Properties props = new Properties();
        if (Files.exists(path)) {
            try (Reader in = Files.newBufferedReader(path)) {
                props.load(in);
            }
        }
        return props;
    }

    // Lists every missing key in one message, so you fix them all in one go.
    public AppConfig require(String... keys) {
        List<String> missing = new ArrayList<>();
        for (String key : keys) {
            if (!values.containsKey(key)) {
                missing.add(key + " (set " + ENV_NAMES.get(key) + " or add it to config.properties)");
            }
        }
        if (!missing.isEmpty()) {
            throw new ConfigException("Missing settings: " + String.join(", ", missing));
        }
        return this;
    }

    public String get(String key) {
        return values.get(key);
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(values.get(key));
    }

    // Every setting, ready to print, with secrets masked.
    public String describe() {
        List<String> parts = new ArrayList<>();
        for (Map.Entry<String, String> e : values.entrySet()) {
            String shown = SECRET_KEYS.contains(e.getKey()) ? Secrets.mask(e.getValue()) : e.getValue();
            parts.add(e.getKey() + "=" + shown);
        }
        return String.join(", ", parts);
    }
}
