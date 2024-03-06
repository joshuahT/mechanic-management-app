package com.mechanic.management.DTO;

import java.util.List;

public class CustomerDTO {
    private Long customerId;
    private Long phoneNumber;
    private String name;
    private String email;
    private String address;

    private List<VehiclesDTO> vehicles;

    public List<VehiclesDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehiclesDTO> vehicles) {
        this.vehicles = vehicles;
    }

    public CustomerDTO() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", phoneNumber=" + phoneNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
