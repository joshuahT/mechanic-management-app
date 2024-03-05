package com.mechanic.management.service;

import com.mechanic.management.DTO.CustomerDTO;
import com.mechanic.management.DTO.OrdersDTO;
import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Vehicles;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CustomerService {
    List<Vehicles> getVehiclesByCustomerId(Long customerId);
    Customer getCustomerById(Long customerId) throws ChangeSetPersister.NotFoundException;

    CustomerDTO createOrUpdateCustomer(CustomerDTO customer);
}
