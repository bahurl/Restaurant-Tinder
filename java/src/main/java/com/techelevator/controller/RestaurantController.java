package com.techelevator.controller;


import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthorized")
@RequestMapping (path = "restaurant/")
public class RestaurantController {

    @Autowired
    private RestaurantDao restaurantDao;

    @GetMapping(path = "search?location={location}")
    private List<Restaurant> getRestaurants(@PathVariable String location){ return restaurantDao.getNearbyRestaurants(location);};

    @GetMapping(path = "search?location={location}type={type}")
    private List<Restaurant> getRestaurants(@PathVariable String location, @PathVariable String type){ return restaurantDao.getRestaurantFilter(location,type);};

}
