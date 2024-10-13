package com.virtusa.springboot.BankingSystemSpringBoot.dto;

import lombok.Data;

@Data
public class ForgotPasswordDto {
    private String email;
    private String newPassword;
}
