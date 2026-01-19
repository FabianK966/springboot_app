package net.javaguides.springboot.service;

import jakarta.persistence.EntityNotFoundException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrdersRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public OrderService(OrdersRepository orderRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(Order order) {
        if (order.getEmployee() == null) {
            throw new ResourceNotFoundException("Order must have an employee");
        }

        // Sicherstellen dass Employee existiert
        Employee employee = employeeRepository.findById(order.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee existiert nicht."));

        order.setEmployee(employee);

        return orderRepository.save(order);
    }

    @Transactional
    public List<Order> createOrders(List<Order> orders) {
        List<Order> saved = new ArrayList<>();

        for (Order order : orders) {
            if (order.getEmployee() == null ) {
                throw new ResourceNotFoundException("Jeder Auftrag benötigt einen gültigen Mitarbeiter");
            }

            Employee emp = employeeRepository.findById(order.getEmployee().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Employee", "id", order.getEmployee().getId()));

            order.setEmployee(emp);
            saved.add(orderRepository.save(order));
        }

        return saved;
    }
}

