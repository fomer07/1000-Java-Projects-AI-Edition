import model.Book;
import service.LibraryService;
import service.UserService;
import util.InputHelper;

import java.util.List;
import java.util.Optional;

public class Main {
    private static final LibraryService libraryService = new LibraryService();
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        System.out.println("Welcome to the Smart library!");

        while (true) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = InputHelper.askInt("Choose: ");

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    if (login()){
                        userMenu();
                    }
                    break;
                case 3:
                    libraryService.saveBooks();
                    userService.saveUsers();
                    System.out.println("📦 Data saved. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void register() {
        String username = InputHelper.ask("Username: ");
        String password = InputHelper.ask("Password: ");
        userService.register(username, password);
    }

    private static boolean login() {
        String username = InputHelper.ask("Username: ");
        String password = InputHelper.ask("Password: ");
        return userService.login(username, password);
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n==== User Menu ====");
            System.out.println("1. List Books");
            System.out.println("2. Add Book");
            System.out.println("3. Search Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. My Borrowed Books");
            System.out.println("7. Logout");

            int choice = InputHelper.askInt("Choose: ");

            switch (choice) {
                case 1:
                    libraryService.listBooks();
                    break;
                case 2:
                    String title = InputHelper.ask("Title: ");
                    String author = InputHelper.ask("Author: ");
                    libraryService.addBook(title, author);
                    break;
                case 3:
                    String keyword = InputHelper.ask("Search keyword: ");
                    List<Book> results = libraryService.searchBooks(keyword);
                    results.forEach(System.out::println);
                    break;
                case 4:
                    String bookId = InputHelper.ask("Enter Book ID (start of UUID): ");
                    Optional<Book> book = libraryService.findAvailableBookById(bookId);
                    if (book.isPresent()) {
                        libraryService.markAsBorrowed(book.get());
                        userService.borrowBook(book.get());
                    } else {
                        System.out.println("Book not found or already borrowed.");
                    }
                    break;
                case 5:
                    String bookIdEntered = InputHelper.ask("Enter Book ID to return: ");
                    userService.returnBook(bookIdEntered);

                    // After returning, mark book as available again
                    // Note: findAvailableBookById looks only for available books,
                    // so we do manual search here:

                    Optional<Book> bookEntered = libraryService.searchBooks("").stream()
                            .filter(b -> b.getId().startsWith(bookIdEntered) && !b.isAvailable())
                            .findFirst();
                    bookEntered.ifPresent(libraryService::markAsReturned);
                    break;
                case 6:
                    userService.listBorrowedBooks();
                    break;
                case 7:
                    userService.logout();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}