package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.model.application.ApplicationType;
import com.virtusa.springboot.BankingSystemSpringBoot.service.application.ApplicationTypeService;

@RestController
@RequestMapping("/admin/applicationType")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class ApplicationTypeController {

    @Autowired
    private ApplicationTypeService applicationTypeService;

    @GetMapping("/getAllApplicationTypes")
    public ResponseEntity<List<ApplicationType>> getApplicationTypes() {
        return ResponseEntity.ok(applicationTypeService.getApplicationTypes());
    }

    @PostMapping("/addApplicationType")
    public ResponseEntity<ApplicationType> addApplicationType(@RequestParam String applicationType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationTypeService.addApplicationType(applicationType));
    }

    @PutMapping("/updateApplicationType/{applicationTypeId}")
    public ResponseEntity<ApplicationType> updateApplicationType(
            @PathVariable("applicationTypeId") Long applicationTypeId, @RequestParam String type) {

        return ResponseEntity.ok(applicationTypeService.updateApplicationType(applicationTypeId,type));
    }

}
