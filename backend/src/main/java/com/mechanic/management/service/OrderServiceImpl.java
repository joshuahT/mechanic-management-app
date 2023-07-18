package com.mechanic.management.service;

import com.mechanic.management.DTO.OrdersDTO;
import com.mechanic.management.model.Orders;
import com.mechanic.management.repository.OrdersRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepo ordersRepo;

    @Autowired
    public OrderServiceImpl(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @Override
    public OrdersDTO getOrderByID(Long Id) throws ChangeSetPersister.NotFoundException {
        Orders ordersEntity = ordersRepo.findById(Id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        OrdersDTO orders = new OrdersDTO();
        orders.setOrderDescription(ordersEntity.getOrderDescription());
        orders.setOrderId(ordersEntity.getOrderId());
        orders.setStatus(ordersEntity.getStatus());
        return orders;
    }

    @Override
    public OrdersDTO postOrder(OrdersDTO ordersDTO) {
        Orders orders = new Orders();
        orders.setOrderName(orders.getOrderName());
        orders.setStatus(ordersDTO.getStatus());

        Orders savedOrder = ordersRepo.saveAndFlush(orders);

        OrdersDTO savedOrders = new OrdersDTO();
        savedOrders.setOrderId(savedOrder.getOrderId());
        savedOrders.setStatus(savedOrder.getStatus());
        savedOrders.setOrderName(savedOrder.getOrderName());

        return savedOrders;
    }
}
