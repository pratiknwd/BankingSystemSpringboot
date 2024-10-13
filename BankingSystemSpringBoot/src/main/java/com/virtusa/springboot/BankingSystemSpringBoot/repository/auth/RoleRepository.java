package com.virtusa.springboot.BankingSystemSpringBoot.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleTitleIgnoreCase(String roleTitle);

    Boolean existsByRoleTitle(String roleTitle);
}
