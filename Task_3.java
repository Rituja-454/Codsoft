import java.util.Scanner;

    
    class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Successfully deposited $" + amount);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Successfully withdrew $" + amount);
                return true;
            } else if (amount > balance) {
                System.out.println("Insufficient balance.");
                return false;
            } else {
                System.out.println("Invalid withdrawal amount.");
                return false;
            }
        }
    }


    class ATM {
        private BankAccount account;

        public ATM(BankAccount account) {
            this.account = account;
        }

        public void showMenu() {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- ATM Menu ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Please choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit(scanner);
                        break;
                    case 3:
                        withdraw(scanner);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        }

        private void checkBalance() {
            System.out.println("Your current balance is $" + account.getBalance());
        }

        private void deposit(Scanner scanner) {
            System.out.print("Enter the amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }

        private void withdraw(Scanner scanner) {
            System.out.print("Enter the amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    
    public class Task_3{
        public static void main(String[] args) {
            BankAccount userAccount = new BankAccount(1000.00); // Initial balance of $1000
            ATM atmMachine = new ATM(userAccount);
            atmMachine.showMenu();
        }
    }



