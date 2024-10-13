package com.virtusa.springboot.BankingSystemSpringBoot.model.application;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name= "Application_Type")
public class ApplicationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_type_id")
    private Long applicationTypeId;

    @Column(name = "application_type")
    private String applicationType;
}
