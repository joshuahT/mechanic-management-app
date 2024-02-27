package com.mechanic.management.service;

import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{

    private final VehiclesRepo vehicleRepository;

    @Autowired
    public CustomerServiceImpl(VehiclesRepo vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public List<Vehicles> getVehiclesByCustomerId(Long customerId) {
        return vehicleRepository.findByCustomerCustomerId(customerId);
    }


}
