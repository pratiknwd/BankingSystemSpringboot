package com.virtusa.springboot.BankingSystemSpringBoot.model.demographic;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "Documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @OneToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;  
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotBlank(message = "Identification number is manadatory")
    @Column(name = "document_identification_number")
    private String documentIdentificationNumber;

    @NotBlank(message = "Document Image link is mandatory")
    @Column(name = "document_link")
    private String documentImageLink;

    
    @Column(name = "upload_date")
    private LocalDate uploadDate;
}
