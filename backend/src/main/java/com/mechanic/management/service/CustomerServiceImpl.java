package com.mechanic.management.service;

import com.mechanic.management.DTO.CustomerDTO;
import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{

    private final VehiclesRepo vehicleRepository;
    private final CustomerRepo customerRepo;


    @Autowired
    public CustomerServiceImpl(VehiclesRepo vehicleRepository,CustomerRepo customerRepo){
        this.vehicleRepository = vehicleRepository;
        this.customerRepo = customerRepo;
    }
    @Override
    public List<Vehicles> getVehiclesByCustomerId(Long customerId) {
        return vehicleRepository.findByCustomerCustomerId(customerId);
    }
    @Override
    public Customer getCustomerById(Long customerId) throws ChangeSetPersister.NotFoundException {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }




}
