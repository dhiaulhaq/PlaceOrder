package com.technicaltest.placeorder.controller;

import com.technicaltest.placeorder.entity.Order;
import com.technicaltest.placeorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{customerId}/place-order")
    public ResponseEntity<Map<String, String>> placeOrder(@PathVariable int customerId) {
        orderService.placeOrder(customerId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Order success!");

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<Order>> all(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (page < 1) {
            throw new IllegalArgumentException("Page index must be 1 or greater.");
        }

        Page<Order> order = orderService.findAll(PageRequest.of(page - 1, size));
        return ResponseEntity.ok(order);
    }
}
