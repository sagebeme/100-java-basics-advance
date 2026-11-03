import java.util.List;

public class SaccoAccount {

    // TODO: private fields: the account number, the balance (whole shillings) and the history

    public SaccoAccount(String accountNumber) {
        // TODO
    }

    public void deposit(int shillings) {
        // TODO: refuse 0 or less; add to the balance; write "Deposit 500" in the history
    }

    public boolean withdraw(int shillings) {
        // TODO: too little money? write "Refused 900: balance 300" and return false.
        // Otherwise take it out, write "Withdrawal 200" and return true.
        return false;
    }

    public int getBalance() {
        // TODO
        return -1;
    }

    public String getAccountNumber() {
        // TODO
        return "";
    }

    public List<String> history() {
        // TODO: a copy nobody can change
        return List.of();
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
