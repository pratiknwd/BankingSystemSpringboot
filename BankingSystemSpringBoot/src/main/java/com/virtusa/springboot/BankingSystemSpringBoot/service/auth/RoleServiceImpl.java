package com.virtusa.springboot.BankingSystemSpringBoot.service.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.Role;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.auth.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByTitle(String roleTitle) {
        return roleRepository.findByRoleTitleIgnoreCase(roleTitle)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Role Title", roleTitle));
    }

    @Override
    public Role addRole(Role role) {
        if (roleRepository.existsByRoleTitle(role.getRoleTitle())) {
            throw new ResourceAlreadyExistsException("Role", "role title", role.getRoleTitle());
        }

        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAlLRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRoleWithId(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new ResourceNotFoundException("Role", "role id ", roleId + "");
        }
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role updateRole(Long roleId, Role updatedRole) {
        if (!roleRepository.existsById(roleId)) {
            throw new ResourceNotFoundException("Role", "role id ", roleId + "");
        }

        if (roleRepository.existsByRoleTitle(updatedRole.getRoleTitle())) {
            throw new ResourceAlreadyExistsException("Role", "role title ", updatedRole.getRoleTitle());
        }

        updatedRole.setRoleId(roleId);
        return roleRepository.save(updatedRole);
    }

}
