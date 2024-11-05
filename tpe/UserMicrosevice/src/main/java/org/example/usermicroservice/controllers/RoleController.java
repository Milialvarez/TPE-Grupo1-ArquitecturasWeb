package org.example.billingmicroservice.controllers;

import org.example.billingmicroservice.entities.Role;
import org.example.billingmicroservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> users = roleService.getAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getUserById(@PathVariable("id") Long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @PostMapping("/")
    public ResponseEntity<Role> save(@RequestBody Role user) {
        Role newRole = roleService.save(user);
        return ResponseEntity.ok(newRole);
    }
}
