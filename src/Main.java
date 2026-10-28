
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount(
                "ACC101",
                "Tejaswini",
                10000,
                5000
        );

        int successfulTransactions = 0;
        int unsuccessfulTransactions = 0;
        int choice = 0;

        ArrayList<Transaction> history = new ArrayList<>();

        do {
            System.out.println("\n===== BANK TRANSACTION SYSTEM =====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Display Account Details");
            System.out.println("4. Exit");
            System.out.println("5. View Transaction History");
            System.out.print("Enter your choice: ");

            // Validate menu input
            if (!sc.hasNextInt()) {
                System.out.println(
                        "Invalid input! Please enter a number."
                );
                sc.next();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    System.out.print("Enter withdrawal amount: Rs.");

                    if (!sc.hasNextDouble()) {
                        System.out.println(
                                "Invalid input! Enter a numeric amount."
                        );
                        sc.next();
                        break;
                    }

                    double withdrawalAmount = sc.nextDouble();

                    try {
                        account.withdraw(withdrawalAmount);
                        successfulTransactions++;

                        history.add(new Transaction(
                                "Withdrawal",
                                withdrawalAmount,
                                "SUCCESS",
                                "Transaction completed"
                        ));

                    } catch (InvalidWithdrawalAmountException |
                             InsufficientBalanceException |
                             WithdrawalLimitExceededException e) {

                        System.out.println(
                                "Transaction failed: " + e.getMessage()
                        );

                        unsuccessfulTransactions++;

                        history.add(new Transaction(
                                "Withdrawal",
                                withdrawalAmount,
                                "FAILED",
                                e.getMessage()
                        ));
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter deposit amount: Rs.");

                    if (!sc.hasNextDouble()) {
                        System.out.println(
                                "Invalid input! Enter a numeric amount."
                        );
                        sc.next();
                        break;
                    }

                    double depositAmount = sc.nextDouble();

                    if (depositAmount <= 0) {
                        String message =
                                "Deposit amount must be greater than zero.";

                        System.out.println(
                                "Transaction failed: " + message
                        );

                        unsuccessfulTransactions++;

                        history.add(new Transaction(
                                "Deposit",
                                depositAmount,
                                "FAILED",
                                message
                        ));

                    } else {
                        account.deposit(depositAmount);
                        successfulTransactions++;

                        history.add(new Transaction(
                                "Deposit",
                                depositAmount,
                                "SUCCESS",
                                "Deposit completed"
                        ));
                    }
                    break;
                }

                case 3: {
                    System.out.println("\n--- Account Details ---");

                    System.out.println(
                            "Account Number: " + account.getAccountNumber()
                    );

                    System.out.println(
                            "Account Holder: "
                                    + account.getAccountHolderName()
                    );

                    System.out.println(
                            "Current Balance: Rs." + account.getBalance()
                    );

                    System.out.println(
                            "Maximum Withdrawal Limit: Rs."
                                    + account.getMaximumWithdrawalLimit()
                    );
                    break;
                }

                case 4: {
                    System.out.println("\n--- Final Summary ---");

                    System.out.println(
                            "Final Balance: Rs." + account.getBalance()
                    );

                    System.out.println(
                            "Successful Transactions: "
                                    + successfulTransactions
                    );

                    System.out.println(
                            "Unsuccessful Transactions: "
                                    + unsuccessfulTransactions
                    );

                    System.out.println(
                            "Thank you for using the system!"
                    );
                    break;
                }

                case 5: {
                    System.out.println("\n--- Transaction History ---");

                    if (history.isEmpty()) {
                        System.out.println(
                                "No transactions recorded yet."
                        );
                    } else {
                        for (Transaction transaction : history) {
                            transaction.displayTransaction();
                        }
                    }
                    break;
                }

                default:
                    System.out.println("Invalid menu choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}
