package com.tap.Servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.DAOImp.OrderDaoImp;
import com.tap.DAOImp.OrderItemDaoImp;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user == null) {
            resp.sendRedirect("login.html");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("cart");
            return;
        }

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user == null) {
            resp.sendRedirect("login.html");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("cart");
            return;
        }

        int restaurantId = cart.getItems().iterator().next().getRestaurantId();
        String paymentMode = req.getParameter("paymentMode");
        String address = req.getParameter("address");

        if (paymentMode == null || paymentMode.isBlank()) {
            paymentMode = "Cash on Delivery";
        }
        if (address == null || address.isBlank()) {
            address = user.getAddress();
        }

        Order order = new Order(
                user.getUserId(),
                restaurantId,
                new Timestamp(System.currentTimeMillis()),
                cart.getTotalPrice(),
                "Placed",
                paymentMode,
                address);

        int orderId = new OrderDaoImp().addOrder(order);
        if (orderId <= 0) {
            req.setAttribute("error", "Unable to place your order. Please try again.");
            req.setAttribute("cart", cart);
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
            return;
        }

        OrderItemDaoImp orderItemDao = new OrderItemDaoImp();
        for (CartItem item : cart.getItems()) {
            double itemTotal = item.getPrice() * item.getQuantity();
            orderItemDao.addOrderItem(new OrderItem(orderId, item.getItemId(), itemTotal));
        }

        session.removeAttribute("cart");
        resp.sendRedirect("order-confirmation?orderId=" + orderId);
    }
}
