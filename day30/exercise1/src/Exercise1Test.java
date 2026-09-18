import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Exercise1Test {

    @Test
    void withdrawingWithinTheBalanceSucceeds() throws InsufficientFundsException {
        Exercise1.withdraw(100, 50);
    }

    @Test
    void withdrawingMoreThanTheBalanceThrows() {
        assertThrows(InsufficientFundsException.class, () -> Exercise1.withdraw(100, 500));
    }

    @Test
    void describeOutcomeCatchesTheExceptionAndDescribesIt() {
        assertEquals("Declined: Cannot withdraw 500.0 from a balance of 100.0", Exercise1.describeOutcome(100, 500));
    }

    @Test
    void describeOutcomeApprovesAValidWithdrawal() {
        assertEquals("Withdrawal approved", Exercise1.describeOutcome(100, 50));
    }
}
