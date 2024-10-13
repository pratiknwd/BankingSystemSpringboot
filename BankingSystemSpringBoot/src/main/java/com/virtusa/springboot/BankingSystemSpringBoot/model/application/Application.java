package com.virtusa.springboot.BankingSystemSpringBoot.model.application;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "Applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "application_type")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApplicationType applicationType;

    @Column(name = "application_number")
    private Long applicationNumber;

    @Column(name = "date_of_application")
    private String dateOfApplication;

    @Column(name = "application_status")
    private String applicationStatus = "Pending";

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
