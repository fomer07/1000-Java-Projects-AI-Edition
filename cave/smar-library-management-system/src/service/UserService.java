package service;

import model.Book;
import model.Borrow;
import model.User;
import util.FileStorage;

import java.util.*;

public class UserService {
    private final Map<String, User> userMap = new HashMap<>();
    private User currentUser;
    private static final String USERS_FILE = "users.dat";

    public UserService() {
        List<User> savedUsers = FileStorage.loadFromFile(USERS_FILE);
        savedUsers.forEach(user -> {
            userMap.put(user.getUsername(), user);
        });
    }

    public boolean register(String username, String password) {
        if (userMap.containsKey(username)) {
            System.out.println("User already exists");
            return false;
        }
        userMap.put(username, new User(username, password));
        System.out.println("User registered successfully");
        saveUsers();
        return true;
    }

    public boolean login(String username, String password) {
        User user = userMap.get(username);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            System.out.println("Logged in successfully"
            + "\nUsername: " + user.getUsername());
            return true;
        }
        System.out.println("Username or password is incorrect");
        return false;
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void borrowBook(Book book) {
        if (currentUser != null) {
            Borrow borrow = new Borrow(book);
            currentUser.getBorrowList().add(borrow);
            System.out.println("Borrowed successfully"
            + "\nUsername: " + currentUser.getUsername()
            + "\nBook: " + book.getTitle());
            saveUsers();
        }
    }

    public void returnBook(String bookId) {
        if (currentUser != null) {
            Optional<Borrow> match = currentUser.getBorrowList().stream()
                    .filter(borrow -> borrow.getBook().getId().startsWith(bookId))
                    .findFirst();
            if (match.isPresent()) {
                Book book = match.get().getBook();
                currentUser.getBorrowList().remove(match.get());
                System.out.println("Returned successfully"
                + "\nUsername: " + currentUser.getUsername()
                + "\nBook: " + book.getTitle());
                saveUsers();
            }else {
                System.out.println("Book not found");
            }
        }
    }

    public void listBorrowedBooks() {
        if (currentUser != null) {
            List<Borrow> borrowList = currentUser.getBorrowList();
            if (borrowList.isEmpty()) {
                System.out.println("No borrowed books");
            } else {
                borrowList.forEach(System.out::println);
            }
        }
    }

    public void saveUsers() {
        FileStorage.saveToFile(new ArrayList<>(userMap.values()), USERS_FILE);
    }








}
