
public class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private double maximumWithdrawalLimit;

    public BankAccount(String accountNumber,
                       String accountHolderName,
                       double balance,
                       double maximumWithdrawalLimit) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.maximumWithdrawalLimit = maximumWithdrawalLimit;
    }

    public void withdraw(double amount)
            throws InvalidWithdrawalAmountException,
            InsufficientBalanceException,
            WithdrawalLimitExceededException {

        if (amount <= 0) {
            throw new InvalidWithdrawalAmountException(
                    "Withdrawal amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient account balance.");
        }

        if (amount > maximumWithdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                    "Withdrawal amount exceeds the maximum limit.");
        }

        balance = balance - amount;

        System.out.println("Transaction successful!");
        System.out.println("Withdrawn amount: Rs." + amount);
        System.out.println("Remaining balance: Rs." + balance);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getMaximumWithdrawalLimit() {
        return maximumWithdrawalLimit;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        balance = balance + amount;

        System.out.println("Deposit successful!");
        System.out.println("Deposited amount: Rs." + amount);
        System.out.println("Updated balance: Rs." + balance);
    }



}
