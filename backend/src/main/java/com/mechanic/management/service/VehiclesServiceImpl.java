package com.mechanic.management.service;

import com.mechanic.management.DTO.VehiclesDTO;
import com.mechanic.management.model.Customer;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.mechanic.management.model.Vehicles;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesServiceImpl implements VehiclesService {

    private final VehiclesRepo vehicleRepo;

    private final CustomerService customerService;

    @Autowired
    public VehiclesServiceImpl(VehiclesRepo vehicleRepo, CustomerService customerService) {
        this.vehicleRepo = vehicleRepo;
        this.customerService = customerService;
    }

    public Vehicles getVehicleById(Long vehicleId) throws ChangeSetPersister.NotFoundException {

        Optional<Vehicles> optionalVehicles = vehicleRepo.findById(vehicleId);

        if (optionalVehicles.isPresent()) {
            return optionalVehicles.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }

    }

    public VehiclesDTO createVehicle(VehiclesDTO vehiclesDTO) throws ChangeSetPersister.NotFoundException {

        Vehicles newVehicle = new Vehicles();
        newVehicle.setMake(vehiclesDTO.getMake());
        newVehicle.setModel((vehiclesDTO.getModel()));
        newVehicle.setYear(vehiclesDTO.getYear());
        newVehicle.setLicensePlate(vehiclesDTO.getLicensePlate());

        Customer customer = customerService.getCustomerById(vehiclesDTO.getCustomerId());

        newVehicle.setCustomer(customer);

        Vehicles savedVehicle = vehicleRepo.save(newVehicle);

        VehiclesDTO savedVehicleDTO = new VehiclesDTO();
        savedVehicleDTO.setVehicleId(savedVehicle.getVehicleId());
        savedVehicleDTO.setMake(savedVehicle.getMake());
        savedVehicleDTO.setModel(savedVehicle.getModel());
        savedVehicleDTO.setYear(savedVehicle.getYear());
        savedVehicleDTO.setLicensePlate(savedVehicle.getLicensePlate());

        return savedVehicleDTO;
    }

    public VehiclesDTO deleteVehicles(Long vehicleId) throws ChangeSetPersister.NotFoundException {
        // Retrieve the vehicle by ID
        Vehicles vehicleToDelete = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Delete the vehicle from the repository
        vehicleRepo.delete(vehicleToDelete);

        // Convert the deleted Vehicles entity back to a DTO and return it
        VehiclesDTO deletedVehicleDTO = new VehiclesDTO();
        deletedVehicleDTO.setVehicleId(vehicleToDelete.getVehicleId());
        deletedVehicleDTO.setMake(vehicleToDelete.getMake());
        deletedVehicleDTO.setModel(vehicleToDelete.getModel());
        deletedVehicleDTO.setYear(vehicleToDelete.getYear());
        deletedVehicleDTO.setLicensePlate(vehicleToDelete.getLicensePlate());
        deletedVehicleDTO.setCustomerId(vehicleToDelete.getCustomer().getId());  // Set customer ID in DTO

        return deletedVehicleDTO;
    }
}