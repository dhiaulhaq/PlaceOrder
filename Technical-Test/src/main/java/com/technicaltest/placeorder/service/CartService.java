package com.technicaltest.placeorder.service;

import com.technicaltest.placeorder.entity.Cart;
import com.technicaltest.placeorder.entity.Customer;
import com.technicaltest.placeorder.entity.Product;
import com.technicaltest.placeorder.repository.CartRepository;
import com.technicaltest.placeorder.repository.CustomerRepository;
import com.technicaltest.placeorder.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    public Cart addProductToCart(int customerId, int productId, int quantity) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }

        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Product foundProduct = product.get();
        int totalPrice = foundProduct.getPrice() * quantity;

        Cart cart = new Cart();
        cart.setCustomer(customer.get());
        cart.setProduct(foundProduct);
        cart.setQuantity(quantity);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public Customer getCart(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        int totalPrice = customer.getItems().stream().mapToInt(Cart::getTotalPrice).sum();
        customer.setTotalPrice(totalPrice);

        return customer;
    }

}
