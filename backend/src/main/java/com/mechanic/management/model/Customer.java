package com.mechanic.management.model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    public Customer(){}

    public Customer(Long customerId, Long phoneNumber, String name, String email, String address){
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.address = address;
    }


    public void setId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return customerId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
