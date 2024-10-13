package com.virtusa.springboot.BankingSystemSpringBoot.service.demographic;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.demographic.DocumentType;

import jakarta.validation.Valid;

public interface DocumentTypeService {

    List<DocumentType> getAllDocumentTypes();

    DocumentType addDocumentType(@Valid DocumentType newDocumentType);

    DocumentType updateDocumentType(Long documentTypeId, String documentType);

    void deleteDocumentType(Long documentTypeId);

}
