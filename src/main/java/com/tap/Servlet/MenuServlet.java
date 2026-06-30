package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImp.MenuDaoImp;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/menu")

public class MenuServlet extends HttpServlet {
	
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String restaurantIdParam = req.getParameter("restaurantId");
		 if (restaurantIdParam == null || restaurantIdParam.isBlank()) {
			 resp.sendRedirect("home");
			 return;
		 }
		 int restaurantId = Integer.parseInt(restaurantIdParam);
		 MenuDaoImp menuDAOImp = new MenuDaoImp();
		 List<Menu> allMenuByRestaurant = menuDAOImp.getMenuByRestaurantById(restaurantId);	
		 
		 req.setAttribute("restaurantId", restaurantId);
		 req.setAttribute("allMenuByRestaurant", allMenuByRestaurant);
		 RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		 rd.forward(req,resp);
	 }

}
