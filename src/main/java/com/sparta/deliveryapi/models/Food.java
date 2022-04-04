package com.sparta.deliveryapi.models;

import com.sparta.deliveryapi.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;

    public Food(FoodDto foodDto, Long restaurantId){
        this.restaurantId=restaurantId;
        this.price=foodDto.getPrice();
        this.name = foodDto.getName();

    }
    public Food(List<FoodDto> foods,Long restaurantId){
        this.restaurantId = restaurantId;
        this.price=foods.get(0).getPrice();
        this.name =foods.get(0).getName();
    }


}
