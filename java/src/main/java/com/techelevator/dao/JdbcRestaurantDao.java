package com.techelevator.dao;

import com.techelevator.model.Restaurant;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JdbcRestaurantDao implements RestaurantDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcRestaurantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Restaurant> getNearbyRestaurants(String location) {
        String sql = "SELECT * from restaurants where city = ? OR zip_code = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location,location);
        List<Restaurant> restaurant = new ArrayList<>();
        while(results.next()) {
            restaurant.add(mapRowToRestaurants(results));
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> getNearbyRestaurants(String location, String type) {
        String sql = "SELECT * from restaurants where city = ? OR zip_code = ? AND type = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location,location, type);
        List<Restaurant> restaurant = new ArrayList<>();
        if(results.next()) {
            restaurant.add(mapRowToRestaurants(results));
        } else {
            throw new RuntimeException("location "+location+" was not found.");
        }
        return restaurant;
    }

    private Restaurant mapRowToRestaurants(SqlRowSet rs) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setAddress1(rs.getString("address1"));
        restaurant.setAddress2(rs.getString("address2"));
        restaurant.setAddress3(rs.getString("address3"));
        restaurant.setCity(rs.getString("city"));
        restaurant.setState(rs.getString("state"));
        restaurant.setName(rs.getString("name"));
        restaurant.setZipCode(rs.getString("zip_code"));
        restaurant.setOpenHour(rs.getString("open_hour"));
        restaurant.setCloseHour(rs.getString("close_hour"));
        restaurant.setType(rs.getString("type"));
        return restaurant;
    }
}
