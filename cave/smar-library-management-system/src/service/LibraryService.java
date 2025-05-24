package service;

import model.Book;
import util.FileStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryService {
    private final List<Book> books = new ArrayList<>();
    private static final String BOOKS_FILE = "books.dat";

    public LibraryService() {
        List<Book> saved = FileStorage.loadFromFile(BOOKS_FILE);
        books.addAll(saved);
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
        System.out.println("Book added: " + book.getTitle() + " " + book.getAuthor());
        saveBooks();
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found");
            return;
        }
        books.forEach(System.out::println);
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Book> findAvailableBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().startsWith(id) && book.isAvailable())
                .findFirst();
    }

    public void markAsBorrowed(Book book) {
        book.setAvailable(false);
        saveBooks();
    }

    public void markAsReturned(Book book) {
        book.setAvailable(true);
        saveBooks();
    }

    public void saveBooks() {
        FileStorage.saveToFile(books, BOOKS_FILE);
    }
}
