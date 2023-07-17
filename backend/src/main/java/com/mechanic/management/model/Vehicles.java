package com.mechanic.management.model;

import javax.persistence.*;

@Entity
@Table(name =  "vehicles")
public class Vehicles {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
