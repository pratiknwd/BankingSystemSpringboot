package com.virtusa.springboot.BankingSystemSpringBoot.repository.demographic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.demographic.DocumentType;
@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long>{

    Boolean existsByDocumentType(String documentType);

}
 