package com.technicaltest.placeorder.repository;

import com.technicaltest.placeorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}