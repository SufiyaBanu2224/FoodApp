package com.tap.Servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.tap.DAOImp.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Get login credentials
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        
        UserDAOImpl userDAO = new UserDAOImpl();

        // Fetch user by email
        User user = userDAO.getUserByEmail(email);

        // Check if user exists
        if (user == null) {

            System.out.println("Email not found!");

            resp.sendRedirect("login.html?error=invalid");

            return;
        }

        // Verify password
        if (BCrypt.checkpw(password, user.getPassword())) {

            // Create session
            session.setAttribute("loggedInUser", user);

            // Optional: Store useful attributes
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            System.out.println("Login Successful");

            resp.sendRedirect("home");

        } else {

            System.out.println("Incorrect Password");

            resp.sendRedirect("login.html?error=invalid");
        }
    }
}