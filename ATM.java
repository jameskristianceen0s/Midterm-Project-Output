import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Account values and transaction variables.
        int pin, cpin, npin, choice;
        int tries, maxWithdraw;
        double balance, deposit, withdraw;
        double dailyWithdraw = 0;
        double totalDeposits = 0;
        double totalWithdrawals = 0;
        double largestWithdrawal = 0;
        
        // These are the assignment's starting values and daily limits.
        pin = 330618;
        balance = 13000.62;
        double dailyLimit = 20000; 
        int limitPinAttempts = 3;
        maxWithdraw = 5; 

        // State variables for authentication and the withdrawal-attempt limit.
        boolean unlocked = false;
        int withdrawAttempts = 0;

        tries = 0;

        // The user gets only three attempts to enter the correct PIN.
        while (tries < limitPinAttempts) {
            System.out.print("Enter your PIN: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid PIN format.");
                sc.next();
                tries++;
                continue;
            }
            cpin = sc.nextInt();
            tries++;
            
            if (cpin == pin) {
                unlocked = true;
                break;
            } else {
                int remaining = limitPinAttempts - tries;
                System.out.println("Wrong PIN. Attempts left: " + remaining);
            }
        }

        // Lock access after all PIN attempts fail.
        if (!unlocked) {
            System.out.println("Card is blocked. Please try again tomorrow.");
            sc.close();
            return;
        }

        // Continue showing the menu until the user chooses Exit.
        do {
            System.out.println("1. Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction Summary");
            System.out.println("6. Exit");
            System.out.print("Enter a choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number from 1 to 6.");
                sc.next();
                choice = 0;
                continue;
            }
            choice = sc.nextInt();

            // Process one menu choice, then return to the menu after every transaction.
            switch (choice) {
                // Case 1: Display the current account balance.
                case 1:
                    System.out.printf("\nCurrent Balance: %.2f%n", balance);
                    break;
            
                // Case 2: Accept only positive deposits and track the total deposited.
                case 2:
                    System.out.print("\nEnter the deposit amount: ");
                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        sc.next();
                        break;
                    }
                    deposit = sc.nextDouble();
            
                    if (deposit > 0) {
                        balance += deposit;
                        totalDeposits += deposit;
                        System.out.printf("Deposited amount: %.2f%n", deposit);
                        System.out.printf("New balance: %.2f%n", balance);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;
            
                // Case 3: Apply every withdrawal rule before changing the balance.
                case 3:
                    if (withdrawAttempts >= maxWithdraw) {
                        System.out.println("Your daily withdrawal attempts limit has been reached.");
                    } else {
                        System.out.print("Enter withdrawal amount: ");
                        if (!sc.hasNextDouble()) {
                            System.out.println("Invalid withdrawal amount.");
                            sc.next();
                            break;
                        }
                        withdraw = sc.nextDouble();
               
                        if (withdraw <= 0) {
                            System.out.println("Invalid withdrawal amount.");
                        } else if (dailyWithdraw + withdraw > dailyLimit) {
                            System.out.println("Your daily withdrawal limit has been reached.");
                            System.out.println("\n");
                        } else if (withdraw > balance) {
                            System.out.println("Insufficient balance.");
                            System.out.println("\n");
                        } else {
                            balance -= withdraw;
                            dailyWithdraw += withdraw;
                            totalWithdrawals += withdraw;
                            withdrawAttempts++;
                            if (withdraw > largestWithdrawal) {
                                largestWithdrawal = withdraw;
                            }
               
                            System.out.println("Withdrawal successful.");
                            System.out.printf("Withdrawn amount: %.2f%n", withdraw);
                            System.out.printf("Remaining balance: %.2f%n", balance);
                        }
                    }
                    break;
                            
                // Case 4: Change the PIN after verifying the current PIN.
                case 4:
                    System.out.print("Enter your current PIN: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid PIN format.");
                        sc.next();
                        break;
                    }
                    cpin = sc.nextInt();
            
                    if (cpin == pin) {
                        System.out.print("Enter your new PIN: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid PIN format.");
                            sc.next();
                            break;
                        }
                        npin = sc.nextInt();
            
                        pin = npin;
                        System.out.println("Your PIN has been successfully changed.");
                        System.out.println("\n");
                    } else {
                        System.out.println("Incorrect current PIN.");
                        System.out.println("\n");
                    }
                    break;
            
                // Case 5: Display all values required by the transaction summary.
                case 5:
                    System.out.println();
                    System.out.println("===== Transaction Summary =====");
                    System.out.printf("Current Balance: %.2f%n", balance);
                    System.out.printf("Total Deposits: %.2f%n", totalDeposits);
                    System.out.printf("Total Withdrawals: %.2f%n", totalWithdrawals);
                    System.out.println("Number of Withdrawals: " + withdrawAttempts);
                    System.out.printf("Largest Withdrawal: %.2f%n", largestWithdrawal);
                    System.out.printf("Remaining Daily Withdrawal Limit: %.2f%n", dailyLimit - dailyWithdraw);
                    break;
            
                // Case 6: Leave the menu.
                case 6:
                    break;
            
                // Invalid choices do not terminate the ATM; they return to the menu.
                default:
                    System.out.println("Invalid choice. Please enter a number in the choices.");
            }
                     
        } while (choice != 6);
                  
        sc.close();
    }
}
