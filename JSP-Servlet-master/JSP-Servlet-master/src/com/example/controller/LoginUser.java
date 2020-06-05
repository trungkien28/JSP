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

@WebServlet(name = "LoginUser")
public class LoginUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);

        try {
            User user = userBean.loginUser();

            // Check username and password is correct or not
            if (user == null) {
                response.sendRedirect("/login.jsp?failureMsg=Username or password is incorrect");
                return;
            }

            // Authenticate user
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect user
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
