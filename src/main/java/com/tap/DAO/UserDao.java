package com.tap.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tap.model.User;

public interface UserDao {
	
	public int addUser(User user) throws SQLException;

	public User getUser(String email);

	public List<User> getAllUsers();

	public void deleteUser(int id);

	public void updateUser(User user);

}
