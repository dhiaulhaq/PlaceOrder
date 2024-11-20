package com.technicaltest.placeorder.repository;

import com.technicaltest.placeorder.entity.Cart;
import com.technicaltest.placeorder.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomer(Customer customer);
}