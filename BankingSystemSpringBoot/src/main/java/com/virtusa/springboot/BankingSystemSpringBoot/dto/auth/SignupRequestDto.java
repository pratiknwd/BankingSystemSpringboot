package com.virtusa.springboot.BankingSystemSpringBoot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SignupRequestDto extends LoginRequestDto {
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
}
