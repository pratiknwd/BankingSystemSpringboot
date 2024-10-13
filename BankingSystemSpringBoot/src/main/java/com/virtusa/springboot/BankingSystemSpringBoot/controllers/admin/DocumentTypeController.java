package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.model.demographic.DocumentType;
import com.virtusa.springboot.BankingSystemSpringBoot.service.demographic.DocumentTypeService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/admin/documetType")
@PreAuthorize("hasRole('ADMIN')")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/getAllDocumentTypes")
    public ResponseEntity<List<DocumentType>> getAllDocumentTypes() {
        return ResponseEntity.ok(documentTypeService.getAllDocumentTypes());
    }

    @PostMapping("/addDocumentType")
    public ResponseEntity<DocumentType> addDocumentType(@Valid @RequestBody DocumentType newDocumentType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentTypeService.addDocumentType(newDocumentType));
    }

    @DeleteMapping("/deleteDocumentType/{documentTypeId}")
    public ResponseEntity<String> deleteDocumentType(@PathVariable("documentTypeId") Long documentTypeId){
        documentTypeService.deleteDocumentType(documentTypeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Documentype with id = " + documentTypeId + " successfully!!.");
    }

    @PutMapping("/updateDocumentType/{documentTypeId}")
    public ResponseEntity<DocumentType> updateDocumentType(@PathVariable("documentTypeId") Long documentTypeId,@RequestParam String documentType){
        return ResponseEntity.status(HttpStatus.OK).body(documentTypeService.updateDocumentType(documentTypeId,documentType));
    }
}
