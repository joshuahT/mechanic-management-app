package com.mechanic.management.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name =  "vehicles")
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicles_vehicle_id_seq")
    @SequenceGenerator(name = "vehicles_vehicle_id_seq", sequenceName = "vehicles_vehicle_id_seq", allocationSize = 1)
    @Column(name = "vehicle_id")
    private Long vehicleId;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "year")
    private Long year;

    @Column(name = "license_plate")
    private String licensePlate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
