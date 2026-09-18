import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRegistryTest {

    @Test
    void registeringAddsAFindableUser() {
        UserRegistry registry = new UserRegistry();
        registry.register("amina@example.com", "Amina");
        assertEquals("Amina", registry.find("amina@example.com").getName());
    }

    @Test
    void cannotRegisterTheSameEmailTwice() {
        UserRegistry registry = new UserRegistry();
        registry.register("amina@example.com", "Amina");
        assertThrows(IllegalStateException.class, () -> registry.register("amina@example.com", "Someone else"));
    }

    @Test
    void updatingAUsersNameChangesWhatIsStored() {
        UserRegistry registry = new UserRegistry();
        User user = registry.register("amina@example.com", "Amina");
        user.updateName("Amina K.");
        assertEquals("Amina K.", registry.find("amina@example.com").getName());
    }

    @Test
    void preferencesFallBackToADefaultWhenUnset() {
        User user = new User("amina@example.com", "Amina");
        assertEquals("light", user.getPreference("theme", "light"));
    }

    @Test
    void deletingRemovesTheUser() {
        UserRegistry registry = new UserRegistry();
        registry.register("amina@example.com", "Amina");
        assertTrue(registry.delete("amina@example.com"));
        assertNull(registry.find("amina@example.com"));
    }

    @Test
    void deletingAnUnknownEmailReturnsFalse() {
        UserRegistry registry = new UserRegistry();
        assertFalse(registry.delete("nobody@example.com"));
    }
}
