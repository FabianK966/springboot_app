package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Orders;
import net.javaguides.springboot.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class OrdersController {


    @Autowired
    private OrdersRepository  ordersRepository;
    @GetMapping("/Orders")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
