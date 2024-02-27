package com.mechanic.management.controller;

import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.OrdersRepo;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class CustomerController {
    private final OrdersRepo orderRepository;
    private final CustomerRepo customerRepository;
    private final VehiclesRepo vehicleRepository;

    public CustomerController(OrdersRepo orderRepository, CustomerRepo customerRepository, VehiclesRepo vehicleRepository){
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Vehicles>> getVehiclesByCustomerId(@PathVariable Long customerId){
        List<Vehicles> vehicles = vehicleRepository.findByCustomerCustomerId(customerId);

        if(vehicles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }


}
