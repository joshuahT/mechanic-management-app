package com.mechanic.management.repository;

import com.mechanic.management.model.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface VehiclesRepo extends JpaRepository<Vehicles, Long> {
    List<Vehicles> findByCustomerCustomerId(long customerId);
}
