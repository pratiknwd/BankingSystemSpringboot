package com.virtusa.springboot.BankingSystemSpringBoot.controllers.controlleradvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ErrorResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.AuthException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.AuthTokenException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.InsufficientFundException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.InvalidDataException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.NullPropertyException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthException(AuthException exception) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(exception.getMessage());
        responseDto.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler({ ResourceNotFoundException.class, ResourceAlreadyExistsException.class,
            NullPropertyException.class, InsufficientFundException.class, InvalidDataException.class })
    public ResponseEntity<ErrorResponseDto> handleResourceException(Exception exception) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(exception.getMessage());
        responseDto.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(AuthTokenException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthTokenResponseEntity(AuthTokenException exception) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(exception.getMessage());
        responseDto.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException exception) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(exception.getMessage());
        responseDto.setErrorCode(HttpStatus.FORBIDDEN.value());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDto);
    }
}
