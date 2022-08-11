package atm;

public class User {
    private String creditCardNumber;
    private final String creditCardType;
    private int balance;
    private final int PIN;

    public User(String creditCardType) {
        this.creditCardType = creditCardType;
        long randomNumber = (long) (Math.random() * 1000000000000L);
        if (creditCardType.equals("Basic")) {
            this.creditCardNumber = String.valueOf(1111000000000000L + randomNumber);
            this.balance = 500;
        } else if (creditCardType.equals("Gold")) {
            this.creditCardNumber = String.valueOf(2222000000000000L + randomNumber);
            this.balance = 1000;
        }
        this.PIN = 1234;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public int getBalance() {
        return balance;
    }

    public int getPIN() {
        return PIN;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User " + creditCardNumber + "\n" +
                "Credit card type: " + creditCardType + "\n" +
                "Balance: " + balance + "\n" +
                "PIN: " + PIN;
    }
}