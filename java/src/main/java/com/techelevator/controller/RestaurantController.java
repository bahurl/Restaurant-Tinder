package com.techelevator.controller;


import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping (path = "restaurant/")
public class RestaurantController {

    private RestaurantDao restaurantDao;

    public RestaurantController(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @GetMapping(path = "search")
    private List<Restaurant> getRestaurants(@RequestParam(value = "location", defaultValue = "") String location, @RequestParam(value = "type", defaultValue = "") String type){
        if(type.isEmpty()){
            return restaurantDao.getNearbyRestaurants(location);
        }else {
            return restaurantDao.getNearbyRestaurants(location, type);
        }
    }

    @PostMapping(path = "save")
    private boolean create(@RequestBody List<Restaurant> restaurants){
        return restaurantDao.createRestaurants(restaurants);
    }

}
