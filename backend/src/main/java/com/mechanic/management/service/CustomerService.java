package com.mechanic.management.service;

import com.mechanic.management.model.Vehicles;

import java.util.List;

public interface CustomerService {
    List<Vehicles> getVehiclesByCustomerId(Long customerId);
}
