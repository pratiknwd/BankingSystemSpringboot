package com.virtusa.springboot.BankingSystemSpringBoot.service.demographic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.demographic.DocumentType;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.demographic.DocumentTypeRepository;

import jakarta.validation.Valid;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    @Override
    public DocumentType addDocumentType(@Valid DocumentType newDocumentType) {
        if (documentTypeRepository.existsByDocumentType(newDocumentType.getDocumentType())) {
            throw new ResourceAlreadyExistsException("DocumentType", "type", newDocumentType.getDocumentType());
        }
        return documentTypeRepository.save(newDocumentType);
    }

    @Override
    public DocumentType updateDocumentType(Long documentTypeId, String documentType) {
        DocumentType oldDocumentType = documentTypeRepository.findById(documentTypeId).orElseThrow(()->new ResourceNotFoundException("DocumentType", "document type id", documentTypeId+""));
        oldDocumentType.setDocumentType(documentType);
        return documentTypeRepository.save(oldDocumentType);
    }

    @Override
    public void deleteDocumentType(Long documentTypeId) {
        if(!documentTypeRepository.existsById(documentTypeId)){
            throw new ResourceNotFoundException("DocumentType", "document type id", documentTypeId+"");
        }
        documentTypeRepository.deleteById(documentTypeId);
    }

}
