package com.mechanic.management.controller;

import com.mechanic.management.DTO.OrdersDTO;
import com.mechanic.management.model.Orders;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.OrdersRepo;
import com.mechanic.management.repository.VehiclesRepo;
import com.mechanic.management.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("orders")
public class OrderController {
    private final OrdersRepo orderRepository;
    private final CustomerRepo customerRepository;
    private final VehiclesRepo vehicleRepository;
    private final OrderService orderService;

    public OrderController(OrdersRepo orderRepository, CustomerRepo customerRepository, VehiclesRepo vehicleRepository,
            OrderService orderService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.orderService = orderService;
    }

    @GetMapping("/active")
    public ResponseEntity<List<Orders>> getActiveOrders() {
        List<Orders> activeOrders = orderRepository.findByStatus(false);
        return ResponseEntity.ok(activeOrders);
    }

    @GetMapping("/past")
    public ResponseEntity<List<Orders>> getPastOrders() {
        List<Orders> pastOrders = orderRepository.findByStatus(true);
        return ResponseEntity.ok(pastOrders);
    }

    @PutMapping("/{id}/update-status")
    public ResponseEntity<String> updateOrdetStatus(@PathVariable Long id, @RequestParam boolean newStatus) throws ChangeSetPersister.NotFoundException{

        Orders existingOrder = orderRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        existingOrder.setStatus(newStatus);
        orderRepository.save(existingOrder);

        return ResponseEntity.ok("Order with ID: "+ id + " status updated to "+ newStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrder(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(orderService.getOrderByID(id));
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> postOrder(@RequestBody OrdersDTO ordersDto) {
        return ResponseEntity.ok(orderService.postOrder(ordersDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) throws ChangeSetPersister.NotFoundException{
         orderRepository.deleteById(id);
         return ResponseEntity.ok("Order with ID: " + id + "deleted");
    }

}
