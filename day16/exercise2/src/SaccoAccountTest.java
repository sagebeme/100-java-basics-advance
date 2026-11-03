import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaccoAccountTest {

    @Test
    void startsEmpty() {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        assertEquals("SAC-0042", account.getAccountNumber());
        assertEquals(0, account.getBalance());
        assertEquals(List.of(), account.history());
    }

    @Test
    void depositsAndWithdrawalsMoveTheBalance() {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        account.deposit(500);
        assertTrue(account.withdraw(200));
        assertEquals(300, account.getBalance());
    }

    @Test
    void canTakeOutEverything() {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        account.deposit(300);
        assertTrue(account.withdraw(300));
        assertEquals(0, account.getBalance());
    }

    @Test
    void refusesToGoBelowZero() {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        account.deposit(300);
        assertFalse(account.withdraw(900));
        assertEquals(300, account.getBalance());
    }

    @Test
    void refusesADepositOfNothingOrLess() {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));
        assertEquals(0, account.getBalance());
    }

    @Test
    void writesDownEverythingInOrder() {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        account.deposit(500);
        account.withdraw(200);
        account.withdraw(900);
        assertEquals(List.of("Deposit 500", "Withdrawal 200", "Refused 900: balance 300"), account.history());
    }

    @Test
    void nobodyCanRewriteTheHistory() {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        account.deposit(500);
        assertThrows(UnsupportedOperationException.class, () -> account.history().clear());
        assertEquals(List.of("Deposit 500"), account.history());
    }
}
