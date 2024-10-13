package com.virtusa.springboot.BankingSystemSpringBoot.repository.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.springboot.BankingSystemSpringBoot.model.application.ApplicationType;

public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, Long> {

    Optional<ApplicationType> findByApplicationType(String applicationType);

    Boolean existsByApplicationType(String applicationType);

    List<ApplicationType> findByApplicationTypeEndingWith(String suffix);

    @Query("SELECT a FROM Application_Type a WHERE a.applicationType NOT LIKE %:suffix")
    List<ApplicationType> findAllByApplicationTypeNotEndingWith(@Param("suffix") String suffix);

}
