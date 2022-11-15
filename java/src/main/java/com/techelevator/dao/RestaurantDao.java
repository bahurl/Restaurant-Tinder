package com.techelevator.dao;

import com.techelevator.model.Restaurant;

import java.util.List;

public interface RestaurantDao {

    List<Restaurant> getNearbyRestaurants(String location);

    List<Restaurant> getNearbyRestaurants(String location, String type);
    boolean createRestaurants(List<Restaurant> restaurants);
}
