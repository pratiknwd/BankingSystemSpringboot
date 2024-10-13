package com.virtusa.springboot.BankingSystemSpringBoot.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.auth.LoginRequestDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.auth.SignupRequestDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.auth.UserDTO;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.NullPropertyException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.Role;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;
import com.virtusa.springboot.BankingSystemSpringBoot.service.auth.RoleService;

@Mapper
public abstract class UserMapper {

    @Autowired
    protected RoleService roleService;

    @Mapping(target = "userRoles", source = "roles", qualifiedByName = "resolveUserToUserDtoRoles")
    public abstract UserDTO toDto(User user);

    @Mapping(target = "roles", source = "userRoles", qualifiedByName = "resolveUserDtoToUserRoleTitles")
    public abstract User toEntity(UserDTO userDTO);

    @Mapping(target = "roles", source = "userRoles", qualifiedByName = "resolveUserDtoToUserRoleTitles")
    @Mapping(target = "email" , ignore = true)
    public abstract User toEntity(LoginRequestDto loginRequestDto);

    @Mapping(target = "roles", source = "userRoles", qualifiedByName = "resolveUserDtoToUserRoleTitles")
    public abstract User toEntity(SignupRequestDto signupRequestDto);

    @Named("resolveUserDtoToUserRoleTitles")
    public Set<Role> resolveUserDtoToUserRoleTitles(List<String> roleTitles) {
        if(roleTitles==null){
            throw new NullPropertyException("LoginRequestDto", "roleTitles");
        }
        Set<Role> roles = new HashSet<>();
        for(String roleTitle : roleTitles){
            roles.add(roleService.findRoleByTitle(roleTitle));
        }
        return roles;
    }

    @Named("resolveUserToUserDtoRoles")
    public List<String> resolveUserToUserDtoRoles(Set<Role> roles){
        List<String> userRoles = new ArrayList<>();
        for(Role role : roles){
            userRoles.add(role.getRoleTitle());
        }
        return userRoles;
    }
}
