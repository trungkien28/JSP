package com.example.dataAccess;

import com.example.entity.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

public class UserDataAccess {
    private PreparedStatement searchStatement, insertStatement;

    public PreparedStatement getSearchStatement() throws SQLException, ClassNotFoundException {
        // Check if search statement is null or not
        if (searchStatement == null) {
            // Get database connection
            Connection connection = new DbConnection().getConnection();

            searchStatement = connection.prepareStatement("SELECT * FROM User WHERE username = ? LIMIT 1");
        }
        return searchStatement;
    }

    public PreparedStatement getInsertStatement() throws SQLException, ClassNotFoundException {
        // Check if insert statement is null or not
        if (insertStatement == null) {
            // Get database connection
            Connection connection = new DbConnection().getConnection();

            insertStatement = connection.prepareStatement("INSERT INTO User (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        }
        return insertStatement;
    }

    public boolean registerUser(String username, String password) throws SQLException, ClassNotFoundException {
        // Check if username is already been taken
        PreparedStatement search = getSearchStatement();
        search.setString(1, username);

        ResultSet user = search.executeQuery();

        if (user.next()) {
            return false;
        }

        // Register user
        PreparedStatement insert = getInsertStatement();
        insert.setString(1, username);
        insert.setString(2, password);

        int status = insert.executeUpdate();
        if (status == 0) {
            return false;
        }

        return true;
    }

    public User loginUser(String username, String password) throws SQLException, ClassNotFoundException {
        // Search user by username
        PreparedStatement search = getSearchStatement();
        search.setString(1, username);

        ResultSet user = search.executeQuery();

        if (!user.next()) {
            return null;
        }

        // Check password is correct or not
        if (!password.equals(user.getString("password"))) {
            return null;
        }

        return new User(
                user.getInt("id"),
                user.getString("username"),
                user.getString("password")
        );
    }
}
