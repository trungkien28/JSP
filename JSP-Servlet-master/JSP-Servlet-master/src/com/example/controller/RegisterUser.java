package com.example.controller;

import com.example.entity.User;
import com.example.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterUser")
public class RegisterUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        // check password is equal to repeat password or not
        if (!password.equals(repeatPassword)) {
            response.sendRedirect("/register.jsp?failureMsg=Repeat password is not equal to password");
            return;
        }

        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);

        try {
            // Register user
            boolean status = userBean.registerUser();

            // If username is already been taken
            if (!status) {
                response.sendRedirect("/register.jsp?failureMsg=This username is already been taken");
                return;
            }

            // Redirect user if register successfully
            response.sendRedirect("login.jsp");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
