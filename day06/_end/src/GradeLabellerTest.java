import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeLabellerTest {

    @Test
    void screenSeventeenAndUpIsAA() {
        assertEquals("AA", GradeLabeller.grade(17));
        assertEquals("AA", GradeLabeller.grade(19));
    }

    @Test
    void screensFifteenAndSixteenAreAB() {
        assertEquals("AB", GradeLabeller.grade(15));
        assertEquals("AB", GradeLabeller.grade(16));
    }

    @Test
    void belowFifteenIsC() {
        assertEquals("C", GradeLabeller.grade(14));
    }

    @Test
    void aPeaberryIsPBWhateverItsSize() {
        assertEquals("PB", GradeLabeller.grade(18, true));
        assertEquals("PB", GradeLabeller.grade(12, true));
    }

    @Test
    void aFlatBeanFallsBackToTheSizeRules() {
        assertEquals("AB", GradeLabeller.grade(16, false));
    }

    @Test
    void labelsAFlatBeanWithItsScreen() {
        assertEquals("Othaya Hills Factory | Grade AA | screen 17", GradeLabeller.label("Othaya Hills", 17, false));
    }

    @Test
    void labelsAPeaberryWithoutAScreen() {
        assertEquals("Othaya Hills Factory | Grade PB", GradeLabeller.label("Othaya Hills", 13, true));
    }
}
