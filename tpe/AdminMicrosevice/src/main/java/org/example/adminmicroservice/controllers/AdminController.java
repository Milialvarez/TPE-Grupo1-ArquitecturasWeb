package org.example.adminmicroservice.controllers;

import org.example.adminmicroservice.models.User;
import org.example.adminmicroservice.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    public ResponseEntity<List<User>> getAdmins() {
        List<User> admins = adminService.getAdmins();
        return ResponseEntity.ok(admins);
    }
}