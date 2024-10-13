package com.virtusa.springboot.BankingSystemSpringBoot.model.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User implements UserDetails {

    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        super(user);
        List<GrantedAuthority> auths = new ArrayList<>();

        for (Role role : user.getRoles()) {

            auths.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleTitle().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
     
}
