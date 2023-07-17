package com.mechanic.management.repository;

import com.mechanic.management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Customer, Long> {
}
