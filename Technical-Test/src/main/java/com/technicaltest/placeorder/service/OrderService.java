package com.technicaltest.placeorder.service;

import com.technicaltest.placeorder.entity.*;
import com.technicaltest.placeorder.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void placeOrder(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Cart> cartItems = cartRepository.findByCustomer(customer);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("No items in cart to place order");
        }

        int totalPrice = cartItems.stream()
                .mapToInt(Cart::getTotalPrice)
                .sum();

        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setStatus("Pending");
        final Order savedOrder = orderRepository.save(newOrder);

        List<OrderItem> orderItems = cartItems.stream()
                .map(cart -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(savedOrder);
                    orderItem.setProduct(cart.getProduct());
                    orderItem.setQuantity(cart.getQuantity());
                    orderItem.setTotalPrice(cart.getTotalPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);

        cartRepository.deleteAll(cartItems);
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
