package atm;

import java.util.*;

public class ATM {
    private int cashAvailable;
    private final LinkedHashMap<Integer, Integer> bills = new LinkedHashMap<>();

    public ATM() {
        this.cashAvailable = 0;
        bills.put(500, 10);
        bills.put(200, 10);
        bills.put(100, 10);
        bills.put(50, 10);
        bills.put(20, 10);
        bills.put(10, 10);
        bills.put(5, 10);
        for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            cashAvailable += key * value;
        }
    }

    public User getUserCreditCardNumber(String creditCardNumber, ArrayList<User> users) {
        for (User user : users) {
            if (user.getCreditCardNumber().equals(creditCardNumber)) {
                return user;
            }
        }
        return null;
    }

    public void showWelcomeScreen(ArrayList<User> users, Scanner input) {
        System.out.println(">>>>>>>>>> Welcome! <<<<<<<<<<");
        while (true) {
            System.out.println("Do you want to insert a credit card? (y/n)");
            String command = input.next();
            switch (command) {
                case "y":
                    enterCard(users, input);
                case "n":
                    return;
                default:
                    System.out.println("Please choose " + "'y'" + " for yes and " + "'n'" + " for no!");
            }
        }
    }

    public void enterCard(ArrayList<User> users, Scanner input) {
        System.out.println("<<<<<<<<<< User Validation >>>>>>>>>>");
        while (true) {
            System.out.println("Please enter a valid card number:");
            String creditCardNumber = input.next();
            User user = getUserCreditCardNumber(creditCardNumber, users);
            if (user != null) {
                while (true) {
                    System.out.println("Please enter your PIN:");
                    int PIN = input.nextInt();
                    if (user.getPIN() == PIN) {
                        System.out.println("Welcome, " + user.getCreditCardNumber() + "!");
                        showUserCommands(input, user, users);
                        return;
                    } else {
                        System.out.println("PIN is incorrect! Please retry!");
                    }
                }
            } else {
                System.out.println("Credit card number is incorrect! Please retry!");
            }
        }
    }

    public void showUserCommands(Scanner input, User user, ArrayList<User> users) {
        while (true) {
            System.out.println("<<<<<<<<<< User Interface >>>>>>>>>>");
            System.out.println("(1) Display balance");
            System.out.println("(2) Withdraw cash");
            System.out.println("(e) Eject card");
            String command = input.next();
            switch (command) {
                case "1":
                    displayBalance(user);
                    break;
                case "2":
                    if (cashAvailable > 0) {
                        withdrawCash(user, input);
                    } else {
                        System.out.println("ATM is out of cash!");
                    }
                    break;
                case "e":
                    System.out.println("Card ejected successfully!");
                    showWelcomeScreen(users, input);
                    return;
                default:
                    System.out.println("Incorrect command!");
            }
        }
    }

    public void displayBalance(User user) {
        System.out.println("<<<<<<<<<< Current Balance >>>>>>>>>>");
        System.out.println("Credit card number: " + user.getCreditCardNumber());
        System.out.println("Credit card type: " + user.getCreditCardType());
        System.out.println("Balance: " + user.getBalance());
    }

    public void withdrawCash(User user, Scanner input) {
        System.out.println("<<<<<<<<<< Withdraw Cash >>>>>>>>>>");
        while (true) {
            System.out.println("Please enter cash amount to withdraw:");
            int amount = input.nextInt();
            if (amount % 10 == 0) {
                if (user.getBalance() >= amount) {
                    if (cashAvailable >= amount) {
                        cashAvailable -= amount;
                        user.setBalance(user.getBalance() - amount);
                        System.out.println("You have successfully withdrawn " + amount + " RON!\nCurrent balance remaining: " + user.getBalance());
                        for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
                            Integer key = entry.getKey();
                            Integer value = entry.getValue();
                            if (amount >= key) {
                                if (value < amount / key) {
                                    System.out.println("Number of " + key + " RON bills" + ": " + value);
                                    entry.setValue(0);
                                    amount = amount - value * key;
                                } else if (value >= amount / key) {
                                    System.out.println("Number of " + key + " RON bills" + ": " + amount / key);
                                    entry.setValue(value - amount / key);
                                    amount = amount % key;
                                }
                            }
                        }
                        return;
                    } else {
                        System.out.println("The machine does not have enough funds, please enter a smaller amount!");
                    }
                } else {
                    System.out.println("Insufficient Funds!");
                    return;
                }
            } else {
                System.out.println("Please enter a valid amount! (500, 200, 100, 50, 20, 10, 5)");
            }
        }
    }
}
