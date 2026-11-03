// Checked: the compiler makes every caller of withdraw decide what to do when the float runs out.
public class InsufficientFundsException extends Exception {

    private final int shortBy;

    public InsufficientFundsException(int balance, int amount) {
        super("Short by KES " + (amount - balance));
        this.shortBy = amount - balance;
    }

    public int getShortBy() {
        return shortBy;
    }
}
