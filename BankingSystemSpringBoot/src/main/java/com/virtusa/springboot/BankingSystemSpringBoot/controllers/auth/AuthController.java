package com.virtusa.springboot.BankingSystemSpringBoot.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ForgotPasswordDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.auth.LoginRequestDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.auth.LoginResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.auth.SignupRequestDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.UserMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.auth.UserRepository;
import com.virtusa.springboot.BankingSystemSpringBoot.service.auth.AuthService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth/")
public class AuthController {

   @Autowired
   private AuthService authService;

   @Autowired
   private UserMapper userMapper;

   @Autowired
   UserRepository userRepository;

   @PostMapping("/signup")
   public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody SignupRequestDto signupRequestDto) {
      authService.registerUser(userMapper.toEntity(signupRequestDto));
      ResponseDto response = new ResponseDto();
      response.setMessage("User Registered Successfully!");
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

   @PostMapping("/login")

   public ResponseEntity<LoginResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
      User user = userMapper.toEntity(loginRequestDto);

      LoginResponseDto loginResponseDto = LoginResponseDto.builder().accessToken(authService.authenticateUser(user))
            .build();
      loginResponseDto.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());

      return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
   }

   @GetMapping("/getAllUsers")
   public ResponseEntity<List<User>> getAllUsers() {
      return ResponseEntity.ok(userRepository.findAll());
   }

   @PutMapping("/resetPassword")
   public ResponseEntity<ResponseDto> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {

      authService.forgotPassword(forgotPasswordDto);

      ResponseDto response = new ResponseDto();
      response.setMessage("Password Changed Successfully.");

      return ResponseEntity.ok().body(response);

   }

}
