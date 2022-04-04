package com.sparta.deliveryapi.models;

import com.sparta.deliveryapi.dto.FoodOrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MyOrder {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String restaurantName;
    @OneToMany
    @JoinColumn
    private List<FoodOrderDto> foods;
    @Column(nullable = false)
    private int deliveryFee;
    @Column(nullable = false)
    private int totalPrice;


    public MyOrder(String restaurantName,List<FoodOrderDto> foods,int deliveryFee,int totalPrice){
        this.restaurantName =restaurantName;
        this.foods=foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}


