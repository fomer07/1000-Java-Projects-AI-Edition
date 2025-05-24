package model;

import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable {
    private final String id;
    private final String title;
    private final String author;
    private boolean available;

    public Book(String title, String author) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s - %s",
                id, title, author, (available ? "Available" : "Borrowed"));
    }
}
