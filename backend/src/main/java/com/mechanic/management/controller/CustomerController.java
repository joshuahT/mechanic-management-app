package com.mechanic.management.controller;

import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.OrdersRepo;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mechanic.management.service.CustomerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Vehicles>> getVehiclesByCustomerId(@PathVariable Long customerId){

        List<Vehicles> vehicles = customerService.getVehiclesByCustomerId(customerId);

        if(vehicles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }


}
