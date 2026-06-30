package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderDao;
import com.tap.model.Order;
import com.tap.utility.DBConnection;

public class OrderDaoImp implements OrderDao {
	
	DBConnection db = new DBConnection();
	private Connection connect = DBConnection.getConnection();	
	private final String ADD_ORDER =
	    "INSERT INTO `order` (userId, restaurantId, orderDate, totalAmount, status, paymentMode, address) "
	  + "VALUES (?, ?, ?, ?, ?, ?, ?)";

	private final String GET_ORDER =
	    "SELECT * FROM `order` WHERE orderId = ?";

	private final String GET_ALL_ORDERS =
	    "SELECT * FROM `order`";

	private final String DELETE_ORDER =
	    "DELETE FROM `order` WHERE orderId = ?";

	private final String UPDATE_ORDER =
	    "UPDATE `order` SET userId=?, restaurantId=?, orderDate=?, totalAmount=?, status=?, paymentMode=?, address=? "
	  + "WHERE orderId=?";



	@Override
	public int addOrder(Order order) {
	    
	    int generatedId = -1;

	    try (PreparedStatement ps = connect.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
	        
	        ps.setInt(1, order.getUserId());
	        ps.setInt(2, order.getRestaurantId());
	        ps.setTimestamp(3, order.getOrderDate());
	        ps.setDouble(4, order.getTotalAmount());
	        ps.setString(5, order.getStatus());
	        ps.setString(6, order.getPaymentMode());
	        ps.setString(7, order.getAddress());

	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            generatedId = rs.getInt(1);
	        }

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    return generatedId;
	}




	@Override
	public Order getOrder(int id) {
		
		Order order = null;
		
		try (PreparedStatement ps = connect.prepareStatement(GET_ORDER)) {
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				order = new Order();
				
				order.setOrderId(rs.getInt("orderId"));
				order.setUserId(rs.getInt("userId"));
				order.setRestaurantId(rs.getInt("restaurantId"));
				order.setOrderDate(rs.getTimestamp("orderDate"));
				order.setTotalAmount(rs.getDouble("totalAmount"));
				order.setStatus(rs.getString("status"));
				order.setPaymentMode(rs.getString("paymentMode"));
				order.setAddress(rs.getString("address"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return order;
	}



	@Override
	public List<Order> getAllOrders() {
		
		List<Order> list = new ArrayList<>();
		
		try (PreparedStatement ps = connect.prepareStatement(GET_ALL_ORDERS);
			 ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				
				Order order = new Order();
				
				order.setOrderId(rs.getInt("orderId"));
				order.setUserId(rs.getInt("userId"));
				order.setRestaurantId(rs.getInt("restaurantId"));
				order.setOrderDate(rs.getTimestamp("orderDate"));
				order.setTotalAmount(rs.getDouble("totalAmount"));
				order.setStatus(rs.getString("status"));
				order.setPaymentMode(rs.getString("paymentMode"));
				order.setAddress(rs.getString("address"));
				
				list.add(order);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}



	@Override
	public void deleteOrder(int id) {
		
		try (PreparedStatement ps = connect.prepareStatement(DELETE_ORDER)) {
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public Order updateOrder(int id) {
		return getOrder(id);
	}

}
