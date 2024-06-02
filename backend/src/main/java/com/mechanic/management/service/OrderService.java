package com.mechanic.management.service;

import com.mechanic.management.DTO.CustomerDTO;
import com.mechanic.management.DTO.OrdersDTO;
import com.mechanic.management.model.Customer;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface OrderService {
    /*
    * This returns a OrderDTO
    * @param ID: the ID of the order
    *
    * */
    OrdersDTO getOrderByID(Long Id) throws ChangeSetPersister.NotFoundException;

    OrdersDTO postOrder(OrdersDTO ordersDTO);


}
