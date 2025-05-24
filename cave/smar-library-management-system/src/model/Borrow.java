package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Borrow implements Serializable {
    private final Book book;
    private final LocalDateTime borrowDate;

    public Borrow(Book book) {
        this.book = book;
        this.borrowDate = LocalDateTime.now();
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String toString() {
        String date = borrowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return String.format("%s borrowed on %s", book, date);
    }
}
