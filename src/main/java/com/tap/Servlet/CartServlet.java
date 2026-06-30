package com.tap.Servlet;

import java.io.IOException;

import com.tap.DAOImp.MenuDaoImp;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Cart cart = getCart(session);

        if (action != null && req.getParameter("itemId") != null) {
            handleAction(action, req, cart);
            resp.sendRedirect("cart");
            return;
        }

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = getCart(session);
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            addToCart(req, cart);
            String restaurantId = req.getParameter("restaurantId");
            if (restaurantId != null && !restaurantId.isBlank()) {
                resp.sendRedirect("menu?restaurantId=" + restaurantId);
                return;
            }
        } else if (action != null) {
            handleAction(action, req, cart);
        }

        resp.sendRedirect("cart");
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private void addToCart(HttpServletRequest req, Cart cart) {
        int menuId = parseInt(req.getParameter("menuId"), 0);
        if (menuId <= 0) {
            return;
        }

        Menu menu = new MenuDaoImp().getMenu(menuId);
        if (menu == null || !menu.isAvailable()) {
            return;
        }

        CartItem item = new CartItem(
                menu.getMenuId(),
                menu.getRestaurantId(),
                1,
                menu.getItemName(),
                menu.getPrice(),
                menu.getDescription(),
                menu.getImagePath());

        cart.addItemToCart(item);
    }

    private void handleAction(String action, HttpServletRequest req, Cart cart) {
        int itemId = parseInt(req.getParameter("itemId"), 0);
        if (itemId <= 0) {
            return;
        }

        CartItem item = new CartItem();
        item.setItemId(itemId);

        if ("increase".equals(action)) {
            cart.updateCartItem(item, "addition");
        } else if ("decrease".equals(action)) {
            cart.updateCartItem(item, "sub");
        } else if ("remove".equals(action)) {
            cart.deleteItem(itemId);
        }
    }

    private int parseInt(String value, int fallback) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return fallback;
        }
    }
}
