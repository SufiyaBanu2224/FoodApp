package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.UserDao;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDao {

    private Connection connect = DBConnection.getConnection();

    private static final String ADD_USER =
            "INSERT INTO user(username,password,email,role,address) VALUES(?,?,?,?,?)";

    private static final String GET_USER_BY_EMAIL =
            "SELECT * FROM user WHERE email=?";

    private static final String GET_ALL_USERS =
            "SELECT * FROM user";

    private static final String DELETE_USER =
            "DELETE FROM user WHERE userId=?";

    private static final String UPDATE_USER =
            "UPDATE user SET password=?, email=?, role=?, address=? WHERE userId=?";

    @Override
    public int addUser(User user) throws SQLException {

        int res = 0;

        try (PreparedStatement ps = connect.prepareStatement(ADD_USER)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getAddress());

            res = ps.executeUpdate();
        }

        return res;
    }

    public User getUserByEmail(String email) {

        User user = null;

        try (PreparedStatement ps = connect.prepareStatement(GET_USER_BY_EMAIL)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int userId = rs.getInt("userId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String address = rs.getString("address");

                user = new User(userId, username, password, email, role, address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUser(String email) {
        return getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        try (PreparedStatement ps = connect.prepareStatement(GET_ALL_USERS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                User user = new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("address"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void updateUser(User user) {

        try (PreparedStatement ps = connect.prepareStatement(UPDATE_USER)) {

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getUserId());

            int res = ps.executeUpdate();

            System.out.println("Rows Updated : " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(int userId) {

        try (PreparedStatement ps = connect.prepareStatement(DELETE_USER)) {

            ps.setInt(1, userId);

            int res = ps.executeUpdate();

            System.out.println("Rows Deleted : " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}