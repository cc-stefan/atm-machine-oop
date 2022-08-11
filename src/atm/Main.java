package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        User basicUser = new User("Basic");
        User goldUser = new User("Gold");
        users.add(basicUser);
        users.add(goldUser);
        for (User user : users) {
            System.out.println(user);
        }
        atm.showWelcomeScreen(users, input);
    }
}