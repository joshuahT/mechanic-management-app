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
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
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
        orders.setCost(ordersEntity.getCost());
        orders.setPrice(ordersEntity.getPrice());
        orders.setOrderName(ordersEntity.getOrderName());

        Customer customerEntity = ordersEntity.getCustomer();
        if (customerEntity != null) {
            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setCustomerId(customerEntity.getId());
            customerDTO.setName(customerEntity.getName());
            customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());
            customerDTO.setEmail(customerEntity.getEmail());
            customerDTO.setAddress(customerEntity.getAddress());

            orders.setCustomer(customerDTO);
        }
        Vehicles vehicleEntity = ordersEntity.getVehicle();
        if(vehicleEntity != null){

            VehiclesDTO vehicleDTO = new VehiclesDTO();

            vehicleDTO.setVehicleId(vehicleEntity.getVehicleId());
            vehicleDTO.setMake(vehicleEntity.getMake());
            vehicleDTO.setModel(vehicleEntity.getModel());
            vehicleDTO.setYear(vehicleEntity.getYear());
            vehicleDTO.setLicensePlate(vehicleEntity.getLicensePlate());
            vehicleDTO.setCustomerId(vehicleEntity.getCustomer().getCustomerId());

            orders.setVehicle(vehicleDTO);

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
            vehiclesDTO = ordersDTO.getVehicle();
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
        savedOrders.setVehicle(vehiclesDTO);


        return savedOrders;
    }

    @Override
    public OrdersDTO putOrder(OrdersDTO ordersDTO) {
        // right now it gives us everything but the customer and vehicles changes for some reason. even though when
        //OrdersDTO{orderId=22, orderName='Test', orderDescription='This is a test', status=false, price=10, cost=1, customer=CustomerDTO{customerId=12, phoneNumber=1111111111, name='John', email='johndoe@gmail.com', address='N/A'}, vehicles=null}
        //OrdersDTO{orderId=22, orderName='Test', orderDescription='This is a test', status=false, price=null, cost=null, customer=CustomerDTO{customerId=12, phoneNumber=null, name='John', email='null', address='null'}, vehicles=VehiclesDTO{vehicleId=7, make='Toyota', model='null', year=null, licensePlate='null'}}

        // from the database, i can see that postOrder saved everything correctly, but for some reason this getbyOrderID is wrong. *BLOCKER; fix this first * Fixed
        System.out.println(ordersDTO);

        try {
            OrdersDTO existingOrder = getOrderByID(ordersDTO.getOrderId());
            System.out.println(existingOrder);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
