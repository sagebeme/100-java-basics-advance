public class AgentFloat {

    private int balance;

    public AgentFloat(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be above 0, not " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
