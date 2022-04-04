package com.sparta.deliveryapi.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
