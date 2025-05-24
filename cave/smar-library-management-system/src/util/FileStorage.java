package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {

    public static <T> void saveToFile(List<T> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
            System.out.println("saved to " + fileName);
        } catch (IOException e){
            System.out.println("failed to save to " + fileName);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("failed to load from " + fileName);
            return new ArrayList<>();
        }
    }
}
