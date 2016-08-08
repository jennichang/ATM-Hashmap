package com.theironyard.charlotte;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    //private static ATM atm = new ATM();
    public static HashMap<String, Double> currentAccounts = new HashMap<>(); // still not 100% clear
    // why does this have to be static.  need to work on understanding static methods.


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        currentAccounts.put("Jennifer", 100.00);
        currentAccounts.put("Ben", 30.00);


        while (true) {

            System.out.println("\nWelcome!");
            System.out.println("Please enter your account name?");

            String account = scanner.nextLine();


            if (!currentAccounts.containsKey(account)) {
                System.out.println("You do not currently have an account.\n Would you like to create one? [y/n]");
                String newAccount = scanner.nextLine();

                if (newAccount.equalsIgnoreCase("y")) {
                    currentAccounts.put(account, 0.00);
                    System.out.println("Your account has been created.  " +
                            "Your current balance is $" + currentAccounts.get(account));

                } else {
                    System.out.println("Thank you");
                    continue;
                }
            } else {
                System.out.println("Hello " + account + "!");

            }

            int session = 1;
            while (session == 1) {

                double balance = currentAccounts.get(account);

                System.out.println("\nWhat would you like to do today?\n" +
                        "1. Check my balance\n" +
                        "2. Make a withdrawal\n" +
                        "3. Make a deposit\n" +
                        "4. Transfer funds\n" +
                        "5. Cancel my account\n" +
                        "6. Log out");

                int activity = scanner.nextInt();

                switch (activity) {
                    case 1:
                        System.out.println("Your current balance is " + balance);
                        break;

                    case 2:
                        System.out.println("How much would you like to withdraw today?");
                        int withdrawAmount = scanner.nextInt(); // want to bring over from atm class, but cant figure out how to bring over balance

                        if (withdrawAmount > balance) {
                            System.out.println("You do not have enough money."); // changed this to error because exception can't continue the loop?
                            break;
                        } else if (withdrawAmount % 5 != 0) {
                            System.out.println("Please enter in multiples of $5, $10 or $20");
                            break;
                        } else {
                            System.out.println("Please take your $" + withdrawAmount);
                            currentAccounts.put(account, currentAccounts.get(account) - withdrawAmount);
                            System.out.println("Your current balance is $" + (balance - withdrawAmount));
                            break;
                        }

                    case 3:
                        System.out.println("How much would you like to deposit today?");
                        int depositAmount = scanner.nextInt();

                        System.out.println("You have deposited $" + depositAmount);
                        System.out.println("Your current balance is $" + (balance + depositAmount));
                        currentAccounts.put(account, currentAccounts.get(account) + depositAmount);
                        break;

                    case 4:
                        scanner.nextLine(); // if i don't have this then it's taking inputs from before, this way it refreshes the input
                        System.out.println("Enter the account name to which you wish to transfer funds.");
                        String accountTransfer = scanner.nextLine();

                        System.out.println("Enter the amount you would like to transfer.");
                        double transferAmount = scanner.nextInt();

                        // add or subtract transfer amount from hashmap
                        currentAccounts.put(account, currentAccounts.get(account) - transferAmount);
                        currentAccounts.put(accountTransfer, currentAccounts.get(accountTransfer) + transferAmount);

                        System.out.println("Your current balance is now $" + currentAccounts.get(account));
                        break;

                    case 5: //
                        System.out.println("Are you sure you want to cancel your account today [y/n]?");
                        scanner.nextLine(); // if i don't have this then it's taking inputs from before.  This way it input refreshes.
                        String cancel = scanner.nextLine();

                        if (cancel.equalsIgnoreCase("y")) {
                            currentAccounts.remove(account);
                            System.out.println("We are sorry to see you go");
                            session = 0;
                        } else {
                            System.out.println("Great, you are a valued customer.");
                        }
                        break;

                    case 6:
                        System.out.println("Thank you and please come again.");
                        session = 0;
                        scanner.nextLine(); // if i don't have this then it's taking inputs from before, this way it refreshes the input
                        break;
                }


            }


        }
    }

}




