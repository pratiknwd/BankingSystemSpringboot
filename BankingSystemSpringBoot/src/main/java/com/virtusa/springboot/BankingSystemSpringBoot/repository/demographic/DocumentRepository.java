package com.virtusa.springboot.BankingSystemSpringBoot.repository.demographic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.demographic.Document;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
	List<Document> findByDocumentTypeDocumentTypeId(Long documentTypeId);

}
