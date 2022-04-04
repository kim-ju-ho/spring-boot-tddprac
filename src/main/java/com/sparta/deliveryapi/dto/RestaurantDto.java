package com.sparta.deliveryapi.dto;

import lombok.Getter;

@Getter
public class RestaurantDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    public RestaurantDto(){

    }

    public RestaurantDto(String name, int minOrderPrice, int deliveryFee){
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee= deliveryFee;
    }
}

