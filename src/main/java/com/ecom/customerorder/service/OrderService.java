package com.ecom.customerorder.service;


import com.ecom.customerorder.exception.ResourceNotFoundException;
import com.ecom.customerorder.model.Order;
import com.ecom.customerorder.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Transactional
    public Order addOrder(Order order) {
        Order savedOrder= repository.save(order);
        System.out.println("Saved order with ID: " + savedOrder.getId()); // ✅ Debugging
        return savedOrder;
    }
    public List<Order> addOrders(List<Order> orders) {
        List<Order> savedOrders = repository.saveAll(orders); // ✅ Save all orders
        savedOrders.forEach(order -> System.out.println("Saved order ID: " + order.getId())); // ✅Debugging
        return savedOrders;
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}

