package com.mechanic.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_order_id_seq")
    @SequenceGenerator(name = "orders_order_id_seq", sequenceName = "orders_order_id_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name ="order_description")
    private String orderDescription;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "price")
    private Long price;

    @Column(name = "cost")
    private Long cost;

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicles vehicle;

    public Orders() {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", cost=" + cost +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                '}';
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicles vehicle) {
        this.vehicle = vehicle;
    }
}
