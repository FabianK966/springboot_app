package net.javaguides.springboot.controller;
import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class OrdersController {

    @Autowired
    private OrdersRepository  ordersRepository;
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @PostMapping("/orders")
    public Order createOrders(@RequestBody Order order) {
        return ordersRepository.save(order);
    }

    @PostMapping("/orders/batch")
    public List<Order> createOrders(@RequestBody List<Order> orders) {
        return ordersRepository.saveAll(orders);
    }
}
