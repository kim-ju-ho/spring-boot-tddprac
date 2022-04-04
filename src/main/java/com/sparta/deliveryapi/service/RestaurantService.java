package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.dto.RestaurantDto;
import com.sparta.deliveryapi.models.Restaurant;
import com.sparta.deliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant registRestaurant(RestaurantDto restaurantDto) {
        // 최소주문 금액 체크
        minOrderChk(restaurantDto.getMinOrderPrice());
        // 기본 배달비 체크
        deliveryFeeChk(restaurantDto.getDeliveryFee());
        Restaurant restaurant =new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);

        return restaurant;

    }
    // 전체 가게 리턴
    public List<Restaurant> restaurants() {
        return restaurantRepository.findAll();
    }
    // 최소금액 체크
    public void minOrderChk(int minOrderPrice){
        if(minOrderPrice <1000 || minOrderPrice >100000){
            throw new IllegalArgumentException();
        }else if(minOrderPrice % 1000 >0){
            throw new IllegalArgumentException();
        }

    }

    public void deliveryFeeChk(int deliveryFee){
        if(deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalArgumentException();
        }else if(deliveryFee % 500 >0){
            throw new IllegalArgumentException();
        }

    }

}
