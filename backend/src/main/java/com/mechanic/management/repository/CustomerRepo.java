package com.mechanic.management.repository;

import com.mechanic.management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN  FETCH c.vehicles")
    List<Customer> findAllWithVehicles();
}