package com.sparta.deliveryapi.dto;

import com.sparta.deliveryapi.models.MyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FoodOrderDto {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;// 주문번호
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int price;
    @ManyToOne
    @JoinColumn
    private MyOrder myOrder;


    public FoodOrderDto(String name, int quantity,int price){
        this.name=name;
        this.quantity= quantity;
        this.price = price;
    }
}


