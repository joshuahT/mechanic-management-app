package com.mechanic.management.repository;

import com.mechanic.management.DTO.OrdersDTO;
import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> findByStatus(boolean status);
    Orders findById(long id);

}
