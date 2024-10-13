package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.Role;
import com.virtusa.springboot.BankingSystemSpringBoot.service.auth.RoleService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/admin/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAlLRoles());
    }

    @PostMapping("/addRole")
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.addRole(role));
    }

    @DeleteMapping("/deleteRole/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable("roleId") Long roleId) {
        roleService.deleteRoleWithId(roleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Role with id " + roleId + " has been deleted successfully!!.");
    }

    @PutMapping("/updateRole/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable("roleId") Long roleId, @Valid @RequestBody Role updatedRole) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(roleId, updatedRole));
    }
}
