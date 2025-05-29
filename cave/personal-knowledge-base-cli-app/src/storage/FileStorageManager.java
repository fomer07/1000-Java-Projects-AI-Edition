package storage;

import model.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorageManager {
    private static final String NOTES_DIR = "notes";

    public FileStorageManager() {
        File dir = new File(NOTES_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public void saveNote(Note note) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(getNoteFile(note.getId()))
        )) {
            oos.writeObject(note);
        } catch (IOException e) {
            System.err.println("Error saving note: " + e.getMessage());
        }
    }

    public List<Note> loadAllNotes() {
        File dir = new File(NOTES_DIR);
        File[] files = dir.listFiles(
                (d,name) -> name.endsWith(".note")
        );

        if (files == null) return new ArrayList<>();

        List<Note> notes = new ArrayList<>();
        for (File file : files) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Note note = (Note) ois.readObject();
                notes.add(note);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading note: " + e.getMessage());
            }
        }
        return notes;
    }
    public boolean deleteNote(String noteId) {
        File file = getNoteFile(noteId);
        return file.exists() && file.delete();
    }

    private File getNoteFile(String noteId) {
        return new File(NOTES_DIR + File.separator + noteId + ".note");
    }
}

