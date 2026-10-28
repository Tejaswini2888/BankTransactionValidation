
public class Transaction {

    private String type;
    private double amount;
    private String status;
    private String message;

    public Transaction(String type, double amount,
                       String status, String message) {
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.message = message;
    }

    public void displayTransaction() {
        System.out.println(
                type + " | Rs." + amount
                        + " | " + status
                        + " | " + message
        );
    }
}
