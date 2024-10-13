package com.virtusa.springboot.BankingSystemSpringBoot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NullPropertyException extends RuntimeException {
    private String resource;
    private String propertyName;

    public NullPropertyException(String resource, String propertyName) {
        super(String.format("%s of %s is Null !!. ", propertyName, resource));
        this.resource = resource;
        this.propertyName = propertyName;
    }

}
