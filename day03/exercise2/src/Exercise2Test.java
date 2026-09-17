import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void eighteenAndOverCanVote() {
        assertTrue(Exercise2.canVote(18));
    }

    @Test
    void underEighteenCannotVote() {
        assertFalse(Exercise2.canVote(17));
    }

    @Test
    void oldEnoughWithALicenseCanDrive() {
        assertTrue(Exercise2.canDrive(16, true));
    }

    @Test
    void oldEnoughWithoutALicenseCannotDrive() {
        assertFalse(Exercise2.canDrive(16, false));
    }

    @Test
    void tooYoungCannotDriveEvenWithALicense() {
        assertFalse(Exercise2.canDrive(15, true));
    }

    @Test
    void studentsGetADiscount() {
        assertTrue(Exercise2.getsDiscount(true, false));
    }

    @Test
    void seniorsGetADiscount() {
        assertTrue(Exercise2.getsDiscount(false, true));
    }

    @Test
    void neitherMeansNoDiscount() {
        assertFalse(Exercise2.getsDiscount(false, false));
    }
}
