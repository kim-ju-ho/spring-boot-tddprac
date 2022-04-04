package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.dto.OrderDto;
import com.sparta.deliveryapi.dto.OrderRequestDto;
import com.sparta.deliveryapi.models.MyOrder;
import com.sparta.deliveryapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class orderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public MyOrder orderFood(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.addOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<MyOrder> getOrderFoods(){
        return orderService.getOrderFoods();
    }
}
