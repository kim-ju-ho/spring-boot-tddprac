package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.dto.FoodDto;
import com.sparta.deliveryapi.models.Food;
import com.sparta.deliveryapi.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public @ResponseBody
    List<Food> registFood(@RequestBody List<FoodDto> foodDto, @PathVariable Long restaurantId){
        List<Food> food = foodService.registFood(foodDto,restaurantId);

        return null;
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> foods(@PathVariable Long restaurantId){

        return foodService.getMenu(restaurantId);
    }


}
