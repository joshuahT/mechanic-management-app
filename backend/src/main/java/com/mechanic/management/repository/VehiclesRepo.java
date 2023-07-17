package com.mechanic.management.repository;

import com.mechanic.management.model.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesRepo extends JpaRepository<Vehicles, Long> {
}
