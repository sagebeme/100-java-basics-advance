public class AgentFloat {

    private int balance;

    public AgentFloat(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) throws InsufficientFundsException {
        // TODO: 0 or less → IllegalArgumentException("Amount must be above 0, not " + amount)
        // TODO: more than the balance → InsufficientFundsException, and the balance doesn't change
    }

    public int getBalance() {
        return balance;
    }
}
