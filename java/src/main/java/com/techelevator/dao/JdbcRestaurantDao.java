package com.techelevator.dao;

import com.techelevator.model.Restaurant;
import com.techelevator.model.Times;
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
        List<Restaurant> restaurants = new ArrayList<>();
        while(results.next()) {
            restaurants.add(mapRowToRestaurants(results));
        }
        for(Restaurant restaurant: restaurants) {
            String resTime = "SELECT df.day_name as day_from, dt.day_name as day_to, rd.time_open as open, rd.time_close as close " +
                    " FROM restaurant_days as rd " +
                    " JOIN days as df on df.day_id = day_from_id " +
                    " JOIN days as dt on dt.day_id = day_to_id" +
                    " WHERE restaurant_id = ?";
            List<Times> times = new ArrayList<>();
            SqlRowSet timeResults = jdbcTemplate.queryForRowSet(resTime, restaurant.getRestaurantId());
            while (timeResults.next()) {
                times.add(mapRowToTimes(timeResults));
            }
            restaurant.setTimesList(times);
            if (times.get(0).isOpen()) {
                restaurant.setOpen(true);
            } else if (times.size() > 1 && times.get(1).isOpen()) {
                restaurant.setOpen(false);
            }
        }
        return restaurants;
    }

    @Override
    public List<Restaurant> getNearbyRestaurants(String location, String type) {
        String sql = "SELECT * from restaurants where upper(city) = ? OR zip_code = ? AND type = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location.toUpperCase(),location, type);
        List<Restaurant> restaurants = new ArrayList<>();
        if(results.next()) {
            restaurants.add(mapRowToRestaurants(results));
        } else {
            throw new RuntimeException("location "+location+" was not found.");
        }
        for(Restaurant restaurant: restaurants){
            String resTime = "SELECT df.day_name as day_from, dt.day_name as day_to, rd.time_open as open, rd.time_close as close " +
                    " FROM restaurant_days as rd " +
                    " JOIN days as df on df.day_id = day_from_id " +
                    " JOIN days as dt on dt.day_id = day_to_id" +
                    " WHERE restaurant_id = ?";
            List<Times> times = new ArrayList<>();
            SqlRowSet timeResults = jdbcTemplate.queryForRowSet(resTime, restaurant.getRestaurantId());
            while(timeResults.next()){
                times.add(mapRowToTimes(timeResults));
            }
            restaurant.setTimesList(times);
            boolean open=false;
            if(times.get(0).isOpen()){
                open = true;
                restaurant.setOpen(open);
            } else if (times.size()>1 && times.get(1).isOpen()) {
                restaurant.setOpen(open);
            }
        }
        return restaurants;
    }
    @Override
    public boolean createRestaurants(List<Restaurant> restaurants){
        String sql = "INSERT INTO restaurants (name, img_url, rating, type, address1, address2, address3, city, state, zip_code, phone)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        for(Restaurant restaurant: restaurants){
            jdbcTemplate.update(sql,restaurant.getName(), restaurant.getImgUrl(), restaurant.getRating(),restaurant.getType(),restaurant.getAddress1(), restaurant.getAddress2(),restaurant.getAddress3(), restaurant.getCity(),restaurant.getState(), restaurant.getZipCode(),restaurant.getPhone());
        }
        return true;
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
        restaurant.setPhone(rs.getString("phone"));
        restaurant.setImgUrl(rs.getString("img_url"));
        restaurant.setRating(rs.getFloat("rating"));
        restaurant.setOpenHour(rs.getString("open_hour"));
        restaurant.setCloseHour(rs.getString("close_hour"));
        restaurant.setType(rs.getString("type"));
        return restaurant;
    }
    private Times mapRowToTimes(SqlRowSet rs){
        Times time = new Times();
        time.setDayFrom(rs.getString("day_from"));
        time.setDayTo(rs.getString("day_to"));
        time.setOpen(rs.getInt("open"));
        time.setClose(rs.getInt("close"));
        return time;
    }

}
