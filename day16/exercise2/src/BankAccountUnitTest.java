import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountUnitTest {

    @Test
    void startsWithAZeroBalance() {
        BankAccount account = new BankAccount("ACC001");
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void depositingIncreasesTheBalance() {
        BankAccount account = new BankAccount("ACC001");
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void withdrawingDecreasesTheBalance() {
        BankAccount account = new BankAccount("ACC001");
        account.deposit(100.0);
        assertTrue(account.withdraw(50.0));
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void cannotWithdrawMoreThanTheBalance() {
        BankAccount account = new BankAccount("ACC001");
        account.deposit(30.0);
        assertFalse(account.withdraw(50.0));
        assertEquals(30.0, account.getBalance());
    }

    @Test
    void keepsAHistoryOfEveryTransaction() {
        BankAccount account = new BankAccount("ACC001");
        account.deposit(100.0);
        account.withdraw(20.0);
        assertEquals(2, account.getTransactions().size());
    }
}
