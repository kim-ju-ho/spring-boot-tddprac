package com.sparta.deliveryapi.repository;


import com.sparta.deliveryapi.dto.FoodOrderDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrderDto,Long> {
}
