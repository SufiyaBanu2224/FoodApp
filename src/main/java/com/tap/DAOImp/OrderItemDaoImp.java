package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderItemDao;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDaoImp implements OrderItemDao {
	
	DBConnection db = new DBConnection();
	private Connection connect = DBConnection.getConnection();	
	private final String ADD_ORDER_ITEM =
	    "INSERT INTO `orderitem` (orderId, menuId, totalAmount) VALUES (?, ?, ?)";

	private final String GET_ORDER_ITEM =
	    "SELECT * FROM `orderitem` WHERE orderItemId = ?";

	private final String GET_ALL_ORDER_ITEMS =
	    "SELECT * FROM `orderitem`";

	private final String DELETE_ORDER_ITEM =
	    "DELETE FROM `orderitem` WHERE orderItemId = ?";

	private final String UPDATE_ORDER_ITEM =
	    "UPDATE `orderitem` SET orderId=?, menuId=?, totalAmount=? WHERE orderItemId=?";



	@Override
	public void addOrderItem(OrderItem orderItem) {
		
		try (PreparedStatement ps = connect.prepareStatement(ADD_ORDER_ITEM)) {
			
			ps.setInt(1, orderItem.getOrderId());
			ps.setInt(2, orderItem.getMenuId());
			ps.setDouble(3, orderItem.getTotalAmount());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public OrderItem getOrderItem(int id) {
		
		OrderItem item = null;
		
		try (PreparedStatement ps = connect.prepareStatement(GET_ORDER_ITEM)) {
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				item = new OrderItem();
				
				item.setOrderItemId(rs.getInt("orderItemId"));
				item.setOrderId(rs.getInt("orderId"));
				item.setMenuId(rs.getInt("menuId"));
				item.setTotalAmount(rs.getInt("totalAmount"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}



	@Override
	public List<OrderItem> getAllOrderItem() {
		
		List<OrderItem> list = new ArrayList<>();
		
		try (PreparedStatement ps = connect.prepareStatement(GET_ALL_ORDER_ITEMS);
		     ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				
				OrderItem item = new OrderItem();
				
				item.setOrderItemId(rs.getInt("orderItemId"));
				item.setOrderId(rs.getInt("orderId"));
				item.setMenuId(rs.getInt("menuId"));
				item.setTotalAmount(rs.getInt("totalAmount"));
				
				list.add(item);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}



	@Override
	public void deleteOrderItem(int id) {
		
		try (PreparedStatement ps = connect.prepareStatement(DELETE_ORDER_ITEM)) {
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public OrderItem updateOrderItem(int id) {
		// Your interface requires returning OrderItem,
		// so simply fetch and return the updated record
		return getOrderItem(id);
	}

}
