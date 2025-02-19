package com.mechanic.management.DTO;

import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Vehicles;

import java.util.List;

public class OrdersDTO {
    private Long orderId;
    private String orderName;
    private String orderDescription;
    private Boolean status;
    private Long price;
    private Long cost;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    private CustomerDTO customer;
    private VehiclesDTO vehicle;

    public OrdersDTO() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public VehiclesDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehiclesDTO vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", cost=" + cost +
                ", customer=" + customer +
                ", vehicles=" + vehicle +
                '}';
    }
}
