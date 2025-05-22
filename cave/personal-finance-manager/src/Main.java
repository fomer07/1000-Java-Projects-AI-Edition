import model.User;
import service.AuthService;
import service.TransactionService;
import storage.FileStorage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            if (currentUser == null) {
                System.out.println("\n1. Register\n2. Login\n0. Exit");
                System.out.print("Choose: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Username: ");
                        String regUser = scanner.nextLine();
                        System.out.print("Password: ");
                        String regPass = scanner.nextLine();
                        currentUser = AuthService.register(regUser, regPass);
                        break;
                    case "2":
                        System.out.print("Username: ");
                        String loginUser = scanner.nextLine();
                        System.out.print("Password: ");
                        String loginPass = scanner.nextLine();
                        currentUser = AuthService.login(loginUser, loginPass);
                        break;
                    case "0":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Wrong choice. Try again.");
                }
            } else {
                System.out.println("\nWelcome, " + currentUser.getUsername());
                System.out.println("1. Add Transaction\n2. List Transactions\n3. Logout");
                System.out.print("Choose: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        TransactionService.addTransaction(currentUser, scanner);
                        FileStorage.saveUser(currentUser); // Save after change
                        break;
                    case "2":
                        TransactionService.listTransactions(currentUser);
                        break;
                    case "3":
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
}