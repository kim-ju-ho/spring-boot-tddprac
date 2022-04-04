package com.sparta.deliveryapi.service;


import com.sparta.deliveryapi.dto.*;
import com.sparta.deliveryapi.models.Food;
import com.sparta.deliveryapi.models.MyOrder;
import com.sparta.deliveryapi.models.Restaurant;
import com.sparta.deliveryapi.repository.FoodOrderRepository;
import com.sparta.deliveryapi.repository.FoodRepository;
import com.sparta.deliveryapi.repository.OrderRepository;
import com.sparta.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;


    @Transactional
    public MyOrder addOrder(OrderRequestDto orderRequestDto) {
        // 레스토랑 이름 가져오기
        Optional<Restaurant> restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId());
        String restuarantName = "";
        // 배달비 초기화
        int deliveryFee =0;
        // 총 가격 초기화
        int totalPrice = 0;

        if(restaurant.isPresent()){
            // 레스토랑 이름
            restuarantName = restaurant.get().getName();
            // 배달비
            deliveryFee = restaurant.get().getDeliveryFee();
        }
        //
        // foodOrderDto 객체 생성
        List<FoodOrderDto> foodOrderList = new ArrayList<>();

        // 음식 정보 가져오기
        for(int i =0;i<orderRequestDto.getFoods().size();i++){
            FoodOrderRequestDto foodOrderRequestDto = orderRequestDto.getFoods().get(i);
            //음식 아이디 한개 저장
            Long foodId = foodOrderRequestDto.getId();
            //음식 갯수 저장
            int foodQuantity =foodOrderRequestDto.getQuantity();
            // 음식 갯수 검증
            foodQuantityChk(foodQuantity);
            // 하나의 음식 객체 받아오기
            Optional<Food>  oneFood= foodRepository.findById(foodId);
            //음식 이름 변수 초기화
            String foodName =  "";
            //음식 가격 변수 초기화
            int foodPrice =0;
            if(oneFood.isPresent()){
                // 음식이름 받아오기
                foodName = oneFood.get().getName();
                // 음식가격 받아오기
                foodPrice=oneFood.get().getPrice();
            }
            // 음식 총 가격
            foodPrice = foodPrice * foodQuantity;
            totalPrice += foodPrice;
            // foodDto 객체생성
            FoodOrderDto FoodOrderDto = new FoodOrderDto(foodName,foodQuantity,foodPrice);
            foodOrderRepository.save(FoodOrderDto);
            foodOrderList.add(FoodOrderDto);
        }
        minOrderPriceChk(totalPrice, restaurant);
        totalPrice += deliveryFee;
        
        MyOrder myOrder = new MyOrder(restuarantName,foodOrderList,deliveryFee,totalPrice);
        
        return orderRepository.save(myOrder);

    }

    // 음식 갯수 검증 ( 0 보다 작으면 안됨)
    public void foodQuantityChk(int quantity){
        if(quantity < 1){
            throw new IllegalArgumentException("음식 주문 수량은 1보다 작을수 없습니다. ");
        }else if(quantity > 100){
            throw new IllegalArgumentException("음식 주문 수량은 100보다 클수 없습니다. ");
        }

    }
    // 초소 주문 금액 검증 
    public void minOrderPriceChk(int totalPrice, Optional<Restaurant> restaurant){
        // 최소 주문 금액 가져오기
        int minOrderPrice = restaurant.get().getMinOrderPrice();
        if(totalPrice < minOrderPrice){
            throw new IllegalArgumentException("최소 주문금액을 확인해 주세요! ");
        }
    }

    public List<MyOrder> getOrderFoods() {
        List<MyOrder> myOrder = orderRepository.findAll();
        return myOrder;
    }
}
