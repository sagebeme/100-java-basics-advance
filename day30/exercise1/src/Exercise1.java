public class Exercise1 {

    public static void withdraw(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Cannot withdraw " + amount + " from a balance of " + balance);
        }
    }

    /**
     * Demonstrates multiple catch blocks and propagation: the caller decides what happens next.
     */
    public static String describeOutcome(double balance, double amount) {
        try {
            withdraw(balance, amount);
            return "Withdrawal approved";
        } catch (InsufficientFundsException e) {
            return "Declined: " + e.getMessage();
        } finally {
            System.out.println("Finished processing withdrawal of " + amount);
        }
    }

    public static void main(String[] args) {
        System.out.println(describeOutcome(100, 50));
        System.out.println(describeOutcome(100, 500));
    }
}
