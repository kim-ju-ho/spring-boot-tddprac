package com.sparta.deliveryapi.repository;

import com.sparta.deliveryapi.models.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<MyOrder,Long> {

}
