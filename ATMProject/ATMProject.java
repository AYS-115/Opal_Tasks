package ATMProject;

import java.util.Locale;
import java.util.Scanner;

public class ATMProject {
    static Scanner input = new Scanner(System.in).useLocale(Locale.US);
    static double balance = 1000.00;
    static int pin = 1234;
    static int depositCount = 0;
    static double depositTotal = 0;
    static int withdrawalCount = 0;
    static double withdrawalTotal = 0;
    static double highestDeposit = 0;
    static double highestWithdrawal = 0;
    static int transferCount = 0;
    static double transferTotal = 0;

    public static void main(String[] args) {
        if (login()) {
            runMenu();
        }
        input.close();
    }

    static boolean login() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.println("Enter PIN");
            int enteredPin = readInt();
            if (enteredPin == pin) {
                System.out.println("Welcome!");
                return true;
            } else {
                attempts--;
                System.out.println("Wrong PIN. Attempts left: " + attempts);
            }
        }
        System.out.println("Card locked");
        return false;
    }

    static void menu() {
        System.out.println("\n1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Account tier");
        System.out.println("5. Savings projection");
        System.out.println("6. Receipt summary");
        System.out.println("7. Change PIN");
        System.out.println("8. Transfer money");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    static void runMenu() {
        int choice;
        do {
            menu();
            choice = readInt();
            switch (choice) {
                case 1: checkBalance(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: accountTier(); break;
                case 5: savingsProjection(); break;
                case 6: receiptSummary(); break;
                case 7: changePin(); break;
                case 8: transfer(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Choose 0 to 8.");
            }
        } while (choice != 0);
    }

    // Shared input methods prevent letters from crashing the program.
    static int readInt() {
        while (!input.hasNextInt()) {
            System.out.println("Please enter a whole number.");
            input.next();
        }
        return input.nextInt();
    }

    static double readAmount() {
        while (true) {
            if (input.hasNextDouble()) {
                double amount = input.nextDouble();
                if (Double.isFinite(amount)) {
                    return amount;
                }
            } else {
                input.next();
            }
            System.out.println("Please enter a valid money amount.");
        }
    }

    static double roundMoney(double amount) {
        return Math.round(amount * 100) / 100.0;
    }

    static void checkBalance() {
        System.out.printf(Locale.US, "Balance: %.2f%n", balance);
    }

    static void deposit() {
        System.out.println("Enter deposit amount");
        double amount = readAmount();
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
        } else if (amount > 5000) {
            System.out.println("Deposit cannot be more than 5000.00.");
        } else {
            balance = roundMoney(balance + amount);
            depositCount++;
            depositTotal = roundMoney(depositTotal + amount);
            if (amount > highestDeposit) {
                highestDeposit = amount;
            }
            System.out.printf(Locale.US, "Deposit successful. Balance: %.2f%n", balance);
        }
    }

    static double withdrawalFee(double amount) {
        if (amount <= 500) {
            return 2.00;
        } else {
            return 5.00;
        }
    }

    static void withdraw() {
        System.out.println("Enter withdrawal amount");
        double amount = readAmount();
        double fee = withdrawalFee(amount);

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
        } else if (amount % 10 != 0) {
            System.out.println("Withdrawal amount must be a multiple of 10.");
        } else if (withdrawalTotal + amount > 1000) {
            System.out.println("Session withdrawal limit is 1000.00.");
        } else if (balance < amount + fee) {
            System.out.println("Insufficient balance to cover the amount and fee.");
        } else {
            balance = roundMoney(balance - amount - fee);
            withdrawalCount++;
            withdrawalTotal = roundMoney(withdrawalTotal + amount);
            if (amount > highestWithdrawal) {
                highestWithdrawal = amount;
            }
            System.out.printf(Locale.US, "Fee: %.2f%n", fee);
            System.out.printf(Locale.US, "Withdrawal successful. Balance: %.2f%n", balance);
        }
    }

    static double interestRate() {
        if (balance < 1000) {
            return 0.01;
        } else if (balance < 5000) {
            return 0.02;
        } else if (balance < 20000) {
            return 0.03;
        } else {
            return 0.04;
        }
    }

    static void accountTier() {
        String tier;
        if (balance < 1000) {
            tier = "Bronze";
        } else if (balance < 5000) {
            tier = "Silver";
        } else if (balance < 20000) {
            tier = "Gold";
        } else {
            tier = "Platinum";
        }
        System.out.printf(Locale.US, "Tier: %s, interest rate: %.0f%%%n",
                tier, interestRate() * 100);
    }

    static void savingsProjection() {
        System.out.println("Enter number of years");
        int years = readInt();
        if (years < 1 || years > 10) {
            System.out.println("Years must be from 1 to 10.");
        } else {
            double projectedBalance = balance;
            double rate = interestRate();
            for (int year = 1; year <= years; year++) {
                projectedBalance = projectedBalance * (1 + rate);
                System.out.printf(Locale.US, "Year %d: %.2f%n", year, projectedBalance);
            }
        }
    }

    static void receiptSummary() {
        System.out.printf(Locale.US, "Deposits: %d, total: %.2f%n",
                depositCount, depositTotal);
        System.out.printf(Locale.US, "Withdrawals: %d, total: %.2f%n",
                withdrawalCount, withdrawalTotal);
        System.out.printf(Locale.US, "Transfers: %d, total: %.2f%n",
                transferCount, transferTotal);
        checkBalance();
        System.out.println("Daily report (current session)");
        System.out.printf(Locale.US, "Highest deposit: %.2f%n", highestDeposit);
        System.out.printf(Locale.US, "Highest withdrawal: %.2f%n", highestWithdrawal);
    }

    static void changePin() {
        System.out.println("Enter old PIN");
        int oldPin = readInt();
        if (oldPin != pin) {
            System.out.println("Wrong old PIN. PIN not changed.");
        } else {
            System.out.println("Enter new PIN");
            int newPin = readInt();
            if (newPin < 1000 || newPin > 9999) {
                System.out.println("New PIN must be from 1000 to 9999.");
            } else {
                System.out.println("Confirm new PIN");
                int confirmation = readInt();
                if (newPin == confirmation) {
                    pin = newPin;
                    System.out.println("PIN changed successfully.");
                } else {
                    System.out.println("PINs do not match. PIN not changed.");
                }
            }
        }
    }

    static void transfer() {
        System.out.println("Enter a 6-digit account number");
        int accountNumber = readInt();
        if (accountNumber < 100000 || accountNumber > 999999) {
            System.out.println("Account number must be 6 digits.");
        } else {
            System.out.println("Enter transfer amount");
            double amount = readAmount();
            double fee = withdrawalFee(amount);
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
            } else if (balance < amount + fee) {
                System.out.println("Insufficient balance to cover the amount and fee.");
            } else {
                balance = roundMoney(balance - amount - fee);
                transferCount++;
                transferTotal = roundMoney(transferTotal + amount);
                System.out.printf(Locale.US, "Fee: %.2f%n", fee);
                System.out.printf(Locale.US, "Transfer to %d successful. Balance: %.2f%n",
                        accountNumber, balance);
            }
        }
    }
}
