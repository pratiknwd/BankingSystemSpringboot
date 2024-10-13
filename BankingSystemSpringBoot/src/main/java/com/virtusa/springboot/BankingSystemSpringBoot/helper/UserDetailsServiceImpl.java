package com.virtusa.springboot.BankingSystemSpringBoot.helper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.CustomUserDetails;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.auth.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in loadUserByUsername Method...");
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(userOptional.get());
    }

}
