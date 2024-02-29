package com.mechanic.management.service;

import com.mechanic.management.DTO.VehiclesDTO;
import com.mechanic.management.model.Vehicles;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface VehiclesService {
    Vehicles getVehicleById(Long vehicleId)throws ChangeSetPersister.NotFoundException;
    VehiclesDTO createVehicle(VehiclesDTO vehiclesDTO)throws ChangeSetPersister.NotFoundException;
    VehiclesDTO deleteVehicles(Long id)throws ChangeSetPersister.NotFoundException;

}
