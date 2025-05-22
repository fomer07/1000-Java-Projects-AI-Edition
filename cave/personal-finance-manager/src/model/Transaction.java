package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {

    private LocalDate transactionDate;
    private String description;
    private String category;
    private double amount;
    private TransactionType transactionType;

    public Transaction(LocalDate transactionDate, String description, String category, double amount, TransactionType transactionType) {
        this.transactionDate = transactionDate;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s: %.2f (%s)", transactionDate, category, description, amount, transactionType);
    }
}
