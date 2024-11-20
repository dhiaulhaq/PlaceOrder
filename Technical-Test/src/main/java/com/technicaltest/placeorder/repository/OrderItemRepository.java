package com.technicaltest.placeorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.technicaltest.placeorder.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}