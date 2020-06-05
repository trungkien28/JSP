package com.example.model;

import com.example.dataAccess.UserDataAccess;
import com.example.entity.User;

import java.sql.SQLException;

public class UserBean {
    private int id;
    private String username, password;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean registerUser() throws SQLException, ClassNotFoundException {
        return new UserDataAccess().registerUser(username, password);
    }

    public User loginUser() throws SQLException, ClassNotFoundException {
        return new UserDataAccess().loginUser(username, password);
    }
}
