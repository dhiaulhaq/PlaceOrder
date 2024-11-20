package com.technicaltest.placeorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.technicaltest.placeorder.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
