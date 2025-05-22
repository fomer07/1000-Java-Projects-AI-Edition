package service;

import model.User;
import storage.FileStorage;



public class AuthService {

    public static User register(String username, String password) {
        if (FileStorage.userExists(username)) {
            System.out.println("User already exists");
            return null;
        }
        User newUser = new User(username, password);
        FileStorage.saveUser(newUser);
        return newUser;
    }

    public static User login(String username, String password) {
        User user = FileStorage.loadUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid username or password");
            return null;
        }
        return user;
    }
}
