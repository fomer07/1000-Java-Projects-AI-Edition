package storage;

import model.User;

import java.io.*;

public class FileStorage {
    private static final String DATA_FOLDER = "users/";

    static {
        new File(DATA_FOLDER).mkdirs(); // Ensure folder exists
    }

    public static void saveUser(User user) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FOLDER + user.getUsername() + ".ser"))) {
            outputStream.writeObject(user);
        } catch (IOException e) {
            System.out.println("Error while adding user " + user.getUsername());
        }
    }
    public static User loadUser(String username) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FOLDER + username + ".ser"))) {
            return (User) inputStream.readObject();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading user " + username);
            return null;
        }
    }

    public static boolean userExists(String username) {
        return new File(DATA_FOLDER + username + ".ser").exists();
    }
}
