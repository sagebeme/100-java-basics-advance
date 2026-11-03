// Checked: the compiler makes every caller of withdraw decide what to do when the float runs out.
public class InsufficientFundsException extends Exception {

    // TODO: remember how much was missing

    public InsufficientFundsException(int balance, int amount) {
        super("TODO"); // TODO: the message is "Short by KES " and the missing amount
    }

    public int getShortBy() {
        // TODO
        return 0;
    }
}
