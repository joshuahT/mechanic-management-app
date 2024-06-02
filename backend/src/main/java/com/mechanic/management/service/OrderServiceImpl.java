package com.mechanic.management.service;

import com.mechanic.management.DTO.CustomerDTO;
import com.mechanic.management.DTO.OrdersDTO;
import com.mechanic.management.DTO.VehiclesDTO;
import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Orders;
import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.OrdersRepo;
import com.mechanic.management.repository.VehiclesRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepo ordersRepo;
    private final CustomerRepo customerRepo;
    private final VehiclesRepo vehicleRepo;

    @Autowired
    public OrderServiceImpl(OrdersRepo ordersRepo, CustomerRepo customerRepo, VehiclesRepo vehicleRepo) {

        this.ordersRepo = ordersRepo;
        this.customerRepo = customerRepo;
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public OrdersDTO getOrderByID(Long Id) throws ChangeSetPersister.NotFoundException {
        Orders ordersEntity = ordersRepo.findById(Id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        OrdersDTO orders = new OrdersDTO();
        orders.setOrderDescription(ordersEntity.getOrderDescription());
        orders.setOrderId(ordersEntity.getOrderId());
        orders.setStatus(ordersEntity.getStatus());
        orders.setOrderName(ordersEntity.getOrderName());
        Customer customerEntity = ordersEntity.getCustomer();
        if (customerEntity != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customerEntity.getId());
            customerDTO.setName(customerEntity.getName());
            // Set other relevant fields from the Customer entity to the CustomerDTO
            orders.setCustomer(customerDTO);
        }
        Vehicles vehicleEntity = ordersEntity.getVehicle();
        if(vehicleEntity != null){
            VehiclesDTO vehicleDTO = new VehiclesDTO();
            vehicleDTO.setVehicleId(vehicleEntity.getVehicleId());
            vehicleDTO.setMake(vehicleEntity.getMake());

            orders.setVehicles(vehicleDTO);

        }
        return orders;
    }

    @Override
    public OrdersDTO postOrder(OrdersDTO ordersDTO) {
        // creates an instance of the model class Orders
        Orders orders = new Orders();
        // set the required attributes from the ordersDTO
        orders.setOrderName(ordersDTO.getOrderName());
        orders.setOrderDescription(ordersDTO.getOrderDescription());
        orders.setPrice(ordersDTO.getPrice());
        orders.setCost(ordersDTO.getCost());
        orders.setStatus(ordersDTO.getStatus());


        CustomerDTO customerDTO;
        VehiclesDTO vehiclesDTO;
        try {
            // Fetch and set the customer
            customerDTO = ordersDTO.getCustomer();
            if (customerDTO != null) {
                Customer customer = customerRepo.findById(customerDTO.getCustomerId())
                        .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
                orders.setCustomer(customer);
            }

            // Fetch and set the vehicle
            vehiclesDTO = ordersDTO.getVehicles();
            if (vehiclesDTO != null) {
                Vehicles vehicle = vehicleRepo.findById(vehiclesDTO.getVehicleId())
                        .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
                orders.setVehicle(vehicle);
            }
        } catch (ChangeSetPersister.NotFoundException e) {
            // Handle the exception as needed, such as logging or rethrowing as a runtime exception
            throw new RuntimeException(e.getMessage(), e);
        }


        // Save the orders object to the database and get the saved state back
        Orders savedOrder = ordersRepo.saveAndFlush(orders);

        // Create a new DTO instance based on the saved state and return it
        OrdersDTO savedOrders = new OrdersDTO();
        savedOrders.setOrderId(savedOrder.getOrderId());
        savedOrders.setOrderName(savedOrder.getOrderName());
        savedOrders.setOrderDescription(savedOrder.getOrderDescription());
        savedOrders.setPrice(savedOrder.getPrice());
        savedOrders.setCost(savedOrder.getCost());
        savedOrders.setStatus(savedOrder.getStatus());
        savedOrders.setCustomer(customerDTO);
        savedOrders.setVehicles(vehiclesDTO);


        return savedOrders;
    }
}
