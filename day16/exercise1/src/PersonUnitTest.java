import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonUnitTest {

    @Test
    void storesTheValuesItWasConstructedWith() {
        Person person = new Person("Amina", 30, "amina@example.com");
        assertEquals("Amina", person.getName());
        assertEquals(30, person.getAge());
        assertEquals("amina@example.com", person.getEmail());
    }

    @Test
    void settersUpdateTheirFields() {
        Person person = new Person("Amina", 30, "amina@example.com");
        person.setAge(31);
        assertEquals(31, person.getAge());
    }
}
