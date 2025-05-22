package service;

import model.Transaction;
import model.TransactionType;
import model.User;

import java.time.LocalDate;
import java.util.Scanner;

public class TransactionService {

    public static void addTransaction(User user, Scanner scanner) {
        System.out.println("Add transaction description: ");
        String description = scanner.nextLine();

        System.out.println("Please add transaction category: ");
        String category = scanner.nextLine();

        System.out.println("Please enter the amount you would like to add:");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("Type (1 = INCOME, 2 = EXPENSE): ");
        int typeChoice = Integer.parseInt(scanner.nextLine());
        TransactionType type = (typeChoice == 1) ? TransactionType.INCOME : TransactionType.EXPENSE;

        Transaction transaction = new Transaction(LocalDate.now(), description, category, amount, type);
        user.addTransaction(transaction);

        System.out.println("Transaction added.");
    }

    public static void listTransactions(User user) {
        if (user.getTransactions().isEmpty()) {
            System.out.println("There are no transactions.");
        }
        else {
            for (Transaction transaction : user.getTransactions()) {
                System.out.println(transaction);
            }
        }
    }
}
