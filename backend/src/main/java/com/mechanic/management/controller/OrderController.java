package com.mechanic.management.controller;

import com.mechanic.management.DTO.OrdersDTO;
import com.mechanic.management.model.Orders;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.OrdersRepo;
import com.mechanic.management.repository.VehiclesRepo;
import com.mechanic.management.service.OrderService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrdersRepo orderRepository;
    private final CustomerRepo customerRepository;
    private final VehiclesRepo vehicleRepository;
    private final OrderService orderService;

    public OrderController(OrdersRepo orderRepository, CustomerRepo customerRepository, VehiclesRepo vehicleRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.orderService = orderService;
    }

    @GetMapping("/active")
    public List<Orders> getActiveOrders() {
        return orderRepository.findByStatus(false);
    }

    @GetMapping("/past")
    public List<Orders> getPastOrders() {
        return orderRepository.findByStatus(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrder(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(orderService.getOrderByID(id));
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> postOrder(@RequestBody OrdersDTO ordersDto) {
        return ResponseEntity.ok(orderService.postOrder(ordersDto));
    }

}

