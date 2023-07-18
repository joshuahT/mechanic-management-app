package com.mechanic.management.DTO;

import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Vehicles;

public class OrdersDTO {
    private Long orderId;
    private String orderName;
    private String orderDescription;
    private Boolean status;
    private CustomerDTO customer;
    private VehiclesDTO vehicles;

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

    public VehiclesDTO getVehicles() {
        return vehicles;
    }

    public void setVehicles(VehiclesDTO vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", status=" + status +
                ", customer=" + customer +
                ", vehicles=" + vehicles +
                '}';
    }
}
