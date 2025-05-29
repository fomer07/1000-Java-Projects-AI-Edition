package service;

import model.Note;
import storage.FileStorageManager;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NoteService {

    private final FileStorageManager fileStorageManager;

    public NoteService() {
        this.fileStorageManager = new FileStorageManager();
    }

    public Note createNote(String title, String content) {
        Note note = new Note(title, content);
        fileStorageManager.saveNote(note);
        return note;
    }

    public List<Note> getAllNotes() {
        return fileStorageManager.loadAllNotes()
                .stream()
                .sorted(Comparator.comparing(Note::getCreatedAt))
                .collect(Collectors.toList());
    }

    public Optional<Note> getNoteById(String id) {
        return fileStorageManager.loadAllNotes()
                .stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }

    public boolean deleteNoteById(String id) {
        return fileStorageManager.deleteNote(id);
    }

    public boolean updateNoteContent(String id, String newContent) {
        Optional<Note> noteOpt = getNoteById(id);
        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            note.setContent(newContent);
            fileStorageManager.saveNote(note);
            return true;
        }
        return false;
    }

    public boolean updateNoteTitle(String id, String newTitle) {
        Optional<Note> noteOpt = getNoteById(id);
        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            note.setTitle(newTitle);
            fileStorageManager.saveNote(note);
            return true;
        }
        return false;
    }


    public List<Note> searchNotes(String keyword) {
        String lowerKeyword = keyword.toLowerCase();

        return getAllNotes().stream()
                .filter(
                        note -> note.getTitle().toLowerCase().contains(lowerKeyword) ||
                                note.getContent().toLowerCase().contains(lowerKeyword)
                )
                .collect(Collectors.toList());
    }

    public void printStatistics() {
        List<Note> notes = getAllNotes();
        int total = notes.size();

        long recentCount = notes.stream()
                .filter(note -> note.getCreatedAt().isAfter(LocalDateTime.now().minusDays(7)))
                .count();

        double avgLength = notes.stream()
                .mapToInt(note -> note.getContent().length())
                .average()
                .orElse(0);

        Optional<String> longestTitle = notes.stream()
                .map(Note::getTitle)
                .max(Comparator.comparingInt(String::length));

        System.out.println("\n--- Note Statistics ---");
        System.out.println("Total notes: " + total);
        System.out.println("Created in last 7 days: " + recentCount);
        System.out.printf("Average note length: %.2f characters%n", avgLength);
        System.out.println("Longest title: " + longestTitle.orElse("N/A"));
    }


}
