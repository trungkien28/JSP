package com.example.controller;

import com.example.model.ProductBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddProduct")
public class AddProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        ProductBean productBean = new ProductBean();
        productBean.setName(name);
        productBean.setDescription(description);

        // Check user is authenticated or not
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return;
        }
        try {
            // Add product
            boolean status = productBean.addProduct();

            if (!status) {
                // Redirect user if failure
                response.sendRedirect("/addProduct.jsp?failureMsg=Something went wrong");
                return;
            }

            // Redirect user if success
            response.sendRedirect("/index.jsp");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
