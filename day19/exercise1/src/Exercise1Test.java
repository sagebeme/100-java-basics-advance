import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void aDogBarks() {
        Animal dog = new Dog("Rex");
        assertEquals("Woof", dog.makeSound());
    }

    @Test
    void aCatMeows() {
        Animal cat = new Cat("Whiskers");
        assertEquals("Meow", cat.makeSound());
    }

    @Test
    void aBirdTweets() {
        Animal bird = new Bird("Tweety");
        assertEquals("Tweet", bird.makeSound());
    }

    @Test
    void everyAnimalRemembersItsOwnName() {
        Animal dog = new Dog("Rex");
        assertEquals("Rex", dog.getName());
    }
}
