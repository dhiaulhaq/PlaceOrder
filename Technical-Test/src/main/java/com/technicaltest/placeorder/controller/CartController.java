package com.technicaltest.placeorder.controller;

import com.technicaltest.placeorder.entity.Cart;
import com.technicaltest.placeorder.entity.Customer;
import com.technicaltest.placeorder.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService orderCartService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable int customerId,
            @RequestParam int productId,
            @RequestParam int quantity) {
        Cart item = orderCartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCart(@PathVariable int customerId) {
        Customer customer = orderCartService.getCart(customerId);
        return ResponseEntity.ok(customer);
    }

}
