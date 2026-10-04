import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostCheckTest {

    @Test
    void aGoodPostHasNoProblems() {
        assertEquals(List.of(), PostCheck.problems("Day 50 of 100 Days of Java! #java", "Yesterday's post"));
    }

    @Test
    void emptyIsTheOnlyProblemWhenThereIsNothing() {
        assertEquals(List.of("Nothing to post."), PostCheck.problems("   ", null));
    }

    @Test
    void listsEveryProblemInOrder() {
        String text = "BUY NOW #A #B #C #D " + "X".repeat(270);
        assertEquals(List.of("Too long: 290 characters (max 280).", "Too many hashtags.", "Don't shout."), PostCheck.problems(text, null));
    }

    @Test
    void shortCapitalsAreFine() {
        assertEquals(List.of(), PostCheck.problems("GOAL!!! 2-1", null));
    }

    @Test
    void noRepeats() {
        assertEquals(List.of("You just posted that."), PostCheck.problems(" Habari za asubuhi ", "habari za asubuhi"));
    }
}
