import java.util.HashMap;
import java.util.Map;

public class UserRegistry {
    private final Map<String, User> usersByEmail = new HashMap<>();

    public User register(String email, String name) {
        if (usersByEmail.containsKey(email)) {
            throw new IllegalStateException("A user with this email is already registered");
        }
        User user = new User(email, name);
        usersByEmail.put(email, user);
        return user;
    }

    public User find(String email) {
        return usersByEmail.get(email);
    }

    public boolean delete(String email) {
        return usersByEmail.remove(email) != null;
    }

    public int size() {
        return usersByEmail.size();
    }
}
