package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.RestaurantDao;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImp implements RestaurantDao {

    private Connection connect = DBConnection.getConnection();

    private final String ADD_RESTAURANT =
            "INSERT INTO restaurant(name,cuisineType,deliveryTime,address,adminUserId,rating,isActive,imagePath) VALUES(?,?,?,?,?,?,?,?)";

    private final String GET_RESTAURANT =
            "SELECT * FROM restaurant WHERE restaurantId=?";

    private final String GET_ALL_RESTAURANTS =
            "SELECT * FROM restaurant";

    private final String DELETE_RESTAURANT =
            "DELETE FROM restaurant WHERE restaurantId=?";

    private final String UPDATE_RESTAURANT =
            "UPDATE restaurant SET name=?, cuisineType=?, deliveryTime=?, address=?, adminUserId=?, rating=?, isActive=?, imagePath=? WHERE restaurantId=?";

    @Override
    public void addRestaurant(Restaurant restaurant) {

        try (PreparedStatement statement =
                     connect.prepareStatement(ADD_RESTAURANT)) {

            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getCuisineType());
            statement.setString(3, restaurant.getDeliveryTime());
            statement.setString(4, restaurant.getAddress());
            statement.setInt(5, restaurant.getAdminUserId());
            statement.setString(6, restaurant.getRating());
            statement.setBoolean(7, restaurant.isActive());
            statement.setString(8, restaurant.getImagePath());

            int res = statement.executeUpdate();

            System.out.println("Restaurant Added : " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int id) {

        Restaurant restaurant = null;
        System.out.println("Connection = " + connect);

        try (PreparedStatement statement =
                     connect.prepareStatement(GET_RESTAURANT)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                restaurant = new Restaurant(
                        rs.getInt("restaurantId"),
                        rs.getString("name"),
                        rs.getString("cuisineType"),
                        rs.getString("deliveryTime"),
                        rs.getString("address"),
                        rs.getInt("adminUserId"),
                        rs.getString("rating"),
                        rs.getBoolean("isActive"),
                        rs.getString("imagePath")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {

        List<Restaurant> restaurantList = new ArrayList<>();

        try {

            // Debug query
//            PreparedStatement ps =
//                       connect.prepareStatement("SELECT * FROM restaurant");
//
//            ResultSet countRs = ps.executeQuery();
//
//            while(countRs.next()) {
//                System.out.println("Restaurant Count = "
//                        + countRs.getInt(1));
//            }

            // Original query
            PreparedStatement statement =
                    connect.prepareStatement(GET_ALL_RESTAURANTS);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {

                Restaurant restaurant = new Restaurant(
                        rs.getInt("restaurantId"),
                        rs.getString("name"),
                        rs.getString("cuisineType"),
                        rs.getString("deliveryTime"),
                        rs.getString("address"),
                        rs.getInt("adminUserId"),
                        rs.getString("rating"),
                        rs.getBoolean("isActive"),
                        rs.getString("imagePath")
                );

                restaurantList.add(restaurant);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return restaurantList;
    }
    @Override
    public void deleteRestaurant(int id) {

        try (PreparedStatement statement =
                     connect.prepareStatement(DELETE_RESTAURANT)) {

            statement.setInt(1, id);

            int res = statement.executeUpdate();

            System.out.println("Restaurant Deleted : " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

        try (PreparedStatement statement =
                     connect.prepareStatement(UPDATE_RESTAURANT)) {

            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getCuisineType());
            statement.setString(3, restaurant.getDeliveryTime());
            statement.setString(4, restaurant.getAddress());
            statement.setInt(5, restaurant.getAdminUserId());
            statement.setString(6, restaurant.getRating());
            statement.setBoolean(7, restaurant.isActive());
            statement.setString(8, restaurant.getImagePath());
            statement.setInt(9, restaurant.getRestaurantId());

            int res = statement.executeUpdate();

            System.out.println("Restaurant Updated : " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
