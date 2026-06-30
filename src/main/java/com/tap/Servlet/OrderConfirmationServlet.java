package com.tap.Servlet;

import java.io.IOException;

import com.tap.DAOImp.OrderDaoImp;
import com.tap.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-confirmation")
public class OrderConfirmationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int orderId = parseInt(req.getParameter("orderId"), 0);
        Order order = orderId > 0 ? new OrderDaoImp().getOrder(orderId) : null;

        if (order == null) {
            resp.sendRedirect("home");
            return;
        }

        req.setAttribute("order", order);
        req.getRequestDispatcher("order-confirmation.jsp").forward(req, resp);
    }

    private int parseInt(String value, int fallback) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return fallback;
        }
    }
}
