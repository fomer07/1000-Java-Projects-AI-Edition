package cli;

import model.Note;
import service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final NoteService noteService = new NoteService();

    public static void main(String[] args) {
        System.out.println("=== Personal Knowledge Base ===");

        boolean exit = false;
        while (!exit) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createNote();
                    break;
                case "2":
                    listNotes();
                    break;
                case "3":
                    viewNote();
                    break;
                case "4":
                    updateNote();
                    break;
                case "5":
                    deleteNote();
                    break;
                case "6":
                    searchNotes();
                    break;
                case "7":
                    noteService.printStatistics();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        System.out.println("Exiting... Goodbye!");
    }

    private static void showMenu() {
        System.out.println("\nChoose an action:");
        System.out.println("1 - Create Note");
        System.out.println("2 - List Notes");
        System.out.println("3 - View Note by ID");
        System.out.println("4 - Update Note");
        System.out.println("5 - Delete Note");
        System.out.println("6 - Search Notes");
        System.out.println("7 - View Note Statistics");
        System.out.println("0 - Exit");
        System.out.print("> ");
    }

    private static void createNote() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.println("Content (end with a blank line):");

        StringBuilder content = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            content.append(line).append("\n");
        }

        Note note = noteService.createNote(title, content.toString().trim());
        System.out.println("Note created with ID: " + note.getId());
    }

    private static void listNotes() {
        List<Note> notes = noteService.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        }

        for (Note note : notes) {
            System.out.println("- " + note.getTitle() + " (ID: " + note.getId() + ")");
        }
    }

    private static void viewNote() {
        System.out.print("Enter note ID: ");
        String id = scanner.nextLine().trim();

        Optional<Note> note = noteService.getNoteById(id);
        if (note.isPresent()) {
            System.out.println("- " + note.get().getTitle() + " (ID: " + note.get().getId() + ")");
            System.out.println(note.get().getContent());
        } else {
            System.out.println("Note not found.");
        }
    }

    private static void updateNote() {
        System.out.print("Enter note ID: ");
        String id = scanner.nextLine().trim();

        Optional<Note> noteOpt = noteService.getNoteById(id);
        if (!noteOpt.isPresent()) {
            System.out.println("Note not found.");
            return;
        }

        System.out.println("What do you want to update?");
        System.out.println("1 - Title");
        System.out.println("2 - Content");
        System.out.print("> ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("New title: ");
            String newTitle = scanner.nextLine().trim();
            boolean success = noteService.updateNoteTitle(id, newTitle);
            System.out.println(success ? "Title updated." : "Failed to update.");
        } else if (choice.equals("2")) {
            System.out.println("New content (end with a blank line):");
            StringBuilder newContent = new StringBuilder();
            String line;
            while (!(line = scanner.nextLine()).isEmpty()) {
                newContent.append(line).append("\n");
            }
            boolean success = noteService.updateNoteContent(id, newContent.toString().trim());
            System.out.println(success ? "Content updated." : "Failed to update.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void deleteNote() {
        System.out.print("Enter note ID to delete: ");
        String id = scanner.nextLine().trim();

        boolean success = noteService.deleteNoteById(id);
        System.out.println(success ? "Note deleted." : "Note not found.");
    }

    private static void searchNotes() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().trim();

        List<Note> matches = noteService.searchNotes(keyword);
        if (matches.isEmpty()) {
            System.out.println("No matching notes found.");
        } else {
            System.out.println("Matching notes:");
            for (Note note : matches) {
                System.out.println("- " + note.getTitle() + " (ID: " + note.getId() + ")");
            }
        }
    }

}
