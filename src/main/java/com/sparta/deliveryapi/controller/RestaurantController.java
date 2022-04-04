package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.dto.RestaurantDto;
import com.sparta.deliveryapi.models.Restaurant;
import com.sparta.deliveryapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService= restaurantService;
    }

   @PostMapping("/restaurant/register")
    public  Restaurant registRestaurant(@RequestBody RestaurantDto restaurantDto){

       return restaurantService.registRestaurant(restaurantDto);
    }


    @GetMapping("/restaurants")
    public List<Restaurant> Restaurants(){

        return restaurantService.restaurants();
    }
}
