package com.example.model;

import com.example.dataAccess.ProductDataAccess;
import com.example.entity.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductBean {
    private int id;
    private String name, description;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        return new ProductDataAccess().getAllProducts();
    }

    public boolean addProduct() throws SQLException, ClassNotFoundException {
        return new ProductDataAccess().addProduct(name, description);
    }
}
