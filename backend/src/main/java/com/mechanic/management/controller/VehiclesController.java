package com.mechanic.management.controller;

import com.mechanic.management.DTO.VehiclesDTO;
import com.mechanic.management.model.Vehicles;
import com.mechanic.management.service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/vehicles")
public class VehiclesController {

    private final VehiclesService vehiclesService;

    @Autowired
    public VehiclesController(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiclesDTO> getVehicleById(@PathVariable Long id) {
        try {
            Vehicles vehicle = vehiclesService.getVehicleById(id);
            VehiclesDTO vehicleDTO = convertToDTO(vehicle);
            return ResponseEntity.ok(vehicleDTO);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<VehiclesDTO> createVehicle(@RequestBody VehiclesDTO vehiclesDTO) {
        try {
            VehiclesDTO createdVehicle = vehiclesService.createVehicle(vehiclesDTO);
            return ResponseEntity.ok(createdVehicle);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehiclesDTO> deleteVehicle(@PathVariable Long id) {
        try {
            VehiclesDTO deletedVehicle = vehiclesService.deleteVehicles(id);
            return ResponseEntity.ok(deletedVehicle);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // You can add more methods for updating, listing, etc., based on your requirements

    private VehiclesDTO convertToDTO(Vehicles vehicle) {
        VehiclesDTO vehicleDTO = new VehiclesDTO();
        vehicleDTO.setVehicleId(vehicle.getVehicleId());
        vehicleDTO.setMake(vehicle.getMake());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setYear(vehicle.getYear());
        vehicleDTO.setLicensePlate(vehicle.getLicensePlate());
        // You may need to set other fields depending on your entity structure
        return vehicleDTO;
    }
}
