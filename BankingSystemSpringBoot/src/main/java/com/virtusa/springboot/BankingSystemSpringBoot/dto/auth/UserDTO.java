package com.virtusa.springboot.BankingSystemSpringBoot.dto.auth;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank
    @Size(min = 5, message = "User name must be at least 5 characters long")
    @Pattern(regexp = "^[.0-9a-zA-Z\\s@-_]+$", message = "User Name should contain only alphabets, Numerics and Special Characters: . @ - _")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character."
    )
    private String password;

    private List<String> userRoles;
}
