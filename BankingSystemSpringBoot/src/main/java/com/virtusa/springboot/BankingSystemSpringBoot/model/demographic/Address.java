package com.virtusa.springboot.BankingSystemSpringBoot.model.demographic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "building_name")
    private String buildingName;

    private String street;

    private String city;

    private String state;

    private String country;

    private Integer zipcode;
}
