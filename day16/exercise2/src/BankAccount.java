public class BankAccount {
    private String accountNumber;
    private double balance;
    
    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }
    
    // TODO: Implement deposit method
    public void deposit(double amount) {
        // Add amount to balance
    }
    
    // TODO: Implement withdraw method
    public void withdraw(double amount) {
        // Subtract amount from balance (check if sufficient funds)
    }
    
    // TODO: Implement getBalance method
    public double getBalance() {
        // Return balance
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}

