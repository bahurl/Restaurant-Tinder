package com.techelevator.controller;


import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping (path = "restaurant/")
//@PreAuthorize("isAuthorized")
public class RestaurantController {

//    @Autowired
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
}
