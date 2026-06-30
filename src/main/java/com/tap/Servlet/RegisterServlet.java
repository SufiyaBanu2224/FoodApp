package com.tap.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.tap.DAOImp.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("========== RegisterServlet Called ==========");

        // Read form data
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String role = req.getParameter("role");

        System.out.println("Username : " + userName);
        System.out.println("Email    : " + email);
        System.out.println("Address  : " + address);
        System.out.println("Role     : " + role);

        // Validate input
        if (userName == null || email == null || password == null
                || address == null || role == null
                || userName.isEmpty() || email.isEmpty()
                || password.isEmpty() || address.isEmpty()
                || role.isEmpty()) {

            System.out.println("Some fields are empty.");
            resp.sendRedirect("register.html");
            return;
        }

        // Encrypt password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        // Create user object
        User user = new User(userName, hashedPassword, email, role, address);

        UserDAOImpl userDAO = new UserDAOImpl();

        try {

            int res = userDAO.addUser(user);

            System.out.println("Rows Inserted : " + res);

            if (res > 0) {
                System.out.println("Registration Successful");
                resp.sendRedirect("login.html");
            } else {
                System.out.println("Registration Failed");
                resp.sendRedirect("register.html");
            }

        } catch (SQLException e) {
            System.out.println("SQLException occurred");
            e.printStackTrace();
            resp.sendRedirect("register.html");
        } catch (Exception e) {
            System.out.println("Unexpected Exception");
            e.printStackTrace();
            resp.sendRedirect("register.html");
        }
    }
}
