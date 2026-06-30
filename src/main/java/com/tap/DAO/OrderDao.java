package com.tap.DAO;

import java.util.List;

import com.tap.model.Order;

public interface OrderDao {
	public int addOrder(Order order);
	public Order getOrder(int id);
	public List<Order> getAllOrders();
	public void deleteOrder(int id);
	public Order updateOrder(int id);
}
  