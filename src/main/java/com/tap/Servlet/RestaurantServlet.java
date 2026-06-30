package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImp.RestaurantDAOImp;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class RestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
        RestaurantDAOImp restaurantDao = new RestaurantDAOImp();

        List<Restaurant> allRestaurants = restaurantDao.getAllRestaurant();

//        for (Restaurant restaurant : allRestaurants) {
//
//            System.out.println(restaurant);
//        }
        req.setAttribute("allRestaurants", allRestaurants);
        RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
        rd.forward(req, resp);
    }
}