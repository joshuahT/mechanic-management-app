package com.mechanic.management.controller;

import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Orders;
import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.OrdersRepo;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrdersRepo orderRepository;
    private final CustomerRepo customerRepository;
    private final VehiclesRepo vehicleRepository;

    public OrderController(OrdersRepo orderRepository, CustomerRepo customerRepository, VehiclesRepo vehicleRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/active")
    public List<Orders> getActiveOrders() {
        return orderRepository.findByStatus(false);
    }

    @GetMapping("/past")
    public List<Orders> getPastOrders() {
        return orderRepository.findByStatus(true);
    }

}

