package com.virtusa.springboot.BankingSystemSpringBoot.service.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ForgotPasswordDto;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.AuthException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.InvalidDataException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.Role;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.auth.UserRepository;
import com.virtusa.springboot.BankingSystemSpringBoot.security.jwt.JwtUtilService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AuthException("Username is already taken!");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AuthException("Email is already in use!");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public String authenticateUser(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role role : user.getRoles()) {

            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleTitle().toUpperCase()));
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), authorities);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        long count = authentication.getAuthorities().stream().filter(authority -> authorities.contains(authority))
                .count();
        if (authorities.size() > count) {
            throw new AuthException("User don't have requested role!!!.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtilService.GenerateToken(user.getUsername(), authorities);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", userName));
    }

    @Override
    public void checkPassword(User user, String password) {
        if (!encoder.matches(password, user.getPassword())) {
            throw new InvalidDataException("Invalid Credentials");
        }
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {

        if (!userRepository.existsByEmail(forgotPasswordDto.getEmail())) {
            throw new ResourceNotFoundException("user", "email", forgotPasswordDto.getEmail());
        }

        User user = userRepository.findByEmail(forgotPasswordDto.getEmail()).get();

        user.setPassword(encoder.encode(forgotPasswordDto.getNewPassword()));
        userRepository.save(user);
    }

}
