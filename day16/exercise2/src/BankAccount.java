import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private List<String> transactions = new ArrayList<>();

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposit: " + amount);
    }

    /**
     * @return true if the withdrawal went through, false if there were insufficient funds.
     */
    public boolean withdraw(double amount) {
        if (amount > balance) {
            transactions.add("Withdraw failed (insufficient funds): " + amount);
            return false;
        }
        balance -= amount;
        transactions.add("Withdraw: " + amount);
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<String> getTransactions() {
        return transactions;
    }
}
