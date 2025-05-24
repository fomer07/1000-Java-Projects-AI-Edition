package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private final String username;
    private final String password;
    private final List<Borrow> borrowList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.borrowList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public List<Borrow> getBorrowList() {
        return borrowList;
    }
}
