package com.example.dataAccess;

import com.example.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDataAccess {
    PreparedStatement getAllStatement, insertStatement;

    public PreparedStatement getInsertStatement() throws SQLException, ClassNotFoundException {
        // Check if insert statement is null or not
        if (insertStatement == null) {
            // Get database connection
            Connection connection = new DbConnection().getConnection();

            insertStatement = connection.prepareStatement("INSERT INTO Product (name, description) VALUES (?, ?)");
        }
        return insertStatement;
    }

    public PreparedStatement getGetAllStatement() throws SQLException, ClassNotFoundException {
        // Check if get all statement is null or not
        if (getAllStatement == null) {
            // Get database connection
            Connection connection = new DbConnection().getConnection();

            getAllStatement = connection.prepareStatement("SELECT * FROM Product");
        }
        return getAllStatement;
    }

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        // Get all products
        PreparedStatement getAll = getGetAllStatement();
        ResultSet resultSet = getAll.executeQuery();

        List<Product> products = new LinkedList<>();
        while(resultSet.next()) {
            products.add(new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")
            ));
        }

        return products;
    }

    public boolean addProduct(String name, String description) throws SQLException, ClassNotFoundException {
        // Insert product
        PreparedStatement insert = getInsertStatement();
        insert.setString(1, name);
        insert.setString(2, description);

        int status = insert.executeUpdate();

        if (status == 0) {
            return false;
        }

        return true;
    }
}
