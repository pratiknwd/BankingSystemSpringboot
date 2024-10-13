package com.virtusa.springboot.BankingSystemSpringBoot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceAlreadyExistsException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceAlreadyExistsException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exist with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
