package com.virtusa.springboot.BankingSystemSpringBoot.service.auth;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ForgotPasswordDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;

public interface AuthService {
    void registerUser(User user);

    String authenticateUser(User entity);

    User getUserByUserName(String userName);

    void checkPassword(User user, String password);

    void forgotPassword(ForgotPasswordDto forgotPasswordDto);
}
