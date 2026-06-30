package com.tap.DAO;

import java.util.List;

import com.tap.model.OrderItem;

public interface OrderItemDao {
	public void addOrderItem(OrderItem orderItem);
	public OrderItem getOrderItem(int id);
	public List<OrderItem> getAllOrderItem();
	public void deleteOrderItem(int id);
	public OrderItem updateOrderItem(int id);
}
  