package com.mechanic.management.controller;

import com.mechanic.management.DTO.CustomerDTO;
import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.OrdersRepo;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mechanic.management.service.CustomerService;
import com.mechanic.management.model.Customer;

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

    @PostMapping("/createOrUpdate")
    public ResponseEntity<CustomerDTO> createOrUpdateCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = customerService.createOrUpdateCustomer(customerDTO);
        return new ResponseEntity<>(savedCustomerDTO, HttpStatus.OK);
    }

    @GetMapping("/allWithVehicles")
    public ResponseEntity<List<CustomerDTO>> getAllCustomerWithVehicles(){
        List<CustomerDTO> customers = customerService.getAllCustomersWithVehicles();

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


}
