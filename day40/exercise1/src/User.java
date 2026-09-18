import java.util.HashMap;
import java.util.Map;

public class User {
    private final String email;
    private String name;
    private final Map<String, String> preferences = new HashMap<>();

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }

    public void updateName(String name) {
        this.name = name;
    }

    public void setPreference(String key, String value) {
        preferences.put(key, value);
    }

    public String getPreference(String key, String defaultValue) {
        return preferences.getOrDefault(key, defaultValue);
    }
}
