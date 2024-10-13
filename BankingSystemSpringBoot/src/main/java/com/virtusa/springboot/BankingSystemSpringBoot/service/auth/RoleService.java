package com.virtusa.springboot.BankingSystemSpringBoot.service.auth;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.Role;

public interface RoleService {
    Role findRoleByTitle(String roleTitle);
    
    Role addRole(Role role);

    List<Role> getAlLRoles();

    void deleteRoleWithId(Long roleId);

    Role updateRole(Long roleId, Role updatedRole);
}
