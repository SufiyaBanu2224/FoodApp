package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDao;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDaoImp implements MenuDao {

	DBConnection db = new DBConnection();
	private Connection connect = DBConnection.getConnection();
	
	private final String ADD_MENU = "INSERT INTO menu (restaurantId, itemName, description, price, isAvailable, imagePath) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";

	private final String GET_MENU = "SELECT * FROM menu WHERE menuId = ?";

	private final String GET_MENU_BY_RESTAURANT = "SELECT * FROM `menu` WHERE `restaurantId` = ?";
	
	private final String GET_ALL_MENU = "SELECT * FROM menu";

	private final String DELETE_MENU = "DELETE FROM menu WHERE menuId = ?";

	// NOTE: Ideal update would take Menu object, but matching your interface:
	private final String UPDATE_MENU = "UPDATE menu SET restaurantId=?, itemName=?, description=?, price=?, isAvailable=?, imagePath=? "
			+ "WHERE menuId=?";

	@Override
	public void addMenu(Menu menu) {

		try (PreparedStatement ps = connect.prepareStatement(ADD_MENU)) {

			ps.setInt(1, menu.getRestaurantId());
			ps.setString(2, menu.getItemName());
			ps.setString(3, menu.getDescription());
			ps.setDouble(4, menu.getPrice());
			ps.setBoolean(5, menu.isAvailable());
			ps.setString(6, menu.getImagePath());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int id) {

		Menu menu = null;

		try (PreparedStatement ps = connect.prepareStatement(GET_MENU)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				menu = new Menu();

				menu.setMenuId(rs.getInt("menuId"));
				menu.setRestaurantId(rs.getInt("restaurantId"));
				menu.setItemName(rs.getString("itemName"));
				menu.setDescription(rs.getString("description"));
				menu.setPrice(rs.getInt("price"));
				menu.setAvailable(rs.getBoolean("isAvailable"));
				menu.setImagePath(rs.getString("imagePath"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return menu;
	}

	@Override
	public List<Menu> getAllMenu() {

		List<Menu> list = new ArrayList<>();

		try (PreparedStatement ps = connect.prepareStatement(GET_ALL_MENU); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				Menu menu = new Menu();

				menu.setMenuId(rs.getInt("menuId"));
				menu.setRestaurantId(rs.getInt("restaurantId"));
				menu.setItemName(rs.getString("itemName"));
				menu.setDescription(rs.getString("description"));
				menu.setPrice(rs.getInt("price"));
				menu.setAvailable(rs.getBoolean("isAvailable"));
				menu.setImagePath(rs.getString("imagePath"));

				list.add(menu);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void deleteMenu(int id) {

		try (PreparedStatement ps = connect.prepareStatement(DELETE_MENU)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu updateMenu(int id) {

		// currently just returning the object
		return getMenu(id);
	}
	
	@Override
	public List<Menu> getMenuByRestaurantById(int id) {

		List<Menu> list = new ArrayList<>();

	    try (
	    		
	         PreparedStatement ps = connect.prepareStatement(GET_MENU_BY_RESTAURANT)) {

	        ps.setInt(1, id);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Menu menu = new Menu();

	            menu.setMenuId(rs.getInt("menuId"));
				menu.setRestaurantId(rs.getInt("restaurantId"));
				menu.setItemName(rs.getString("itemName"));
				menu.setDescription(rs.getString("description"));
				menu.setPrice(rs.getInt("price"));
				menu.setAvailable(rs.getBoolean("isAvailable"));
				menu.setImagePath(rs.getString("imagePath"));
				
				list.add(menu);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}


}
