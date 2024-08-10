import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class BankAccount {
    private double balance;
    private String pin;
    private List<String> transactions;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: $" + amount);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: $" + amount);
            System.out.println("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void printTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\nWelcome to the ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
    }

    public void checkBalance() {
        System.out.println("Your current balance is $" + account.getBalance());
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void printTransactions() {
        account.printTransactions();
    }

    public boolean verifyPin(String enteredPin) {
        return account.verifyPin(enteredPin);
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(500.0, "1234"); 
        ATM atm = new ATM(account);

        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        if (!atm.verifyPin(enteredPin)) {
            System.out.println("Invalid PIN. Exiting...");
            return;
        }

        boolean exit = false;
        while (!exit) {
            atm.displayMenu();
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    atm.printTransactions();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
