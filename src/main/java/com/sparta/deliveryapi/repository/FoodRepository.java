package com.sparta.deliveryapi.repository;

import com.sparta.deliveryapi.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findAllByRestaurantIdAndName(Long restaurantId,String name);
    List<Food> findAllByRestaurantId(Long restaurantId);

}
