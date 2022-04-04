package com.sparta.deliveryapi.repository;

import com.sparta.deliveryapi.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

}
