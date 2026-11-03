import java.util.ArrayList;
import java.util.List;

public class SaccoAccount {

    private final String accountNumber;
    private int balance;
    private final List<String> history = new ArrayList<>();

    public SaccoAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(int shillings) {
        if (shillings <= 0) {
            throw new IllegalArgumentException("A deposit must be more than 0, not " + shillings);
        }
        balance += shillings;
        history.add("Deposit " + shillings);
    }

    public boolean withdraw(int shillings) {
        if (shillings > balance) {
            history.add("Refused " + shillings + ": balance " + balance);
            return false;
        }
        balance -= shillings;
        history.add("Withdrawal " + shillings);
        return true;
    }

    public int getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<String> history() {
        return List.copyOf(history);
    }

    public static void main(String[] args) {
        SaccoAccount account = new SaccoAccount("SAC-0042");
        account.deposit(500);
        account.withdraw(200);
        account.withdraw(900);
        System.out.println(account.getAccountNumber() + ": KES " + account.getBalance());
        account.history().forEach(System.out::println);
    }
}
