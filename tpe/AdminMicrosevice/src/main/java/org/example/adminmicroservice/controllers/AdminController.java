package org.example.adminmicroservice.controllers;

import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.Monopatin;
import org.example.adminmicroservice.models.User;
import org.example.adminmicroservice.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/xViajes/{xViajes}/anio/{anio}")
    public ResponseEntity<?> getMonopatinesPorViajesPorAnio(@PathVariable("anio") Integer anio, @PathVariable("xViajes") Integer xViajes){
        List<Monopatin> admins = adminService.getMonopatinesPorViajesPorAnio(anio, xViajes);
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/null")
    public ResponseEntity<?> anullateAccount(@RequestBody Account ac){
       return ResponseEntity.ok(adminService.anullateAccount(ac));
    }

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end){
        try {
            Optional<Object[]> reporteTotalFacturadoEntreFechas = (Optional<Object[]>) adminService.getTotalBilled(origin, end);
            if (reporteTotalFacturadoEntreFechas.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(reporteTotalFacturadoEntreFechas);
        } catch (Exception e) {
            if (origin.isAfter(end)) return ResponseEntity.badRequest().body(e.getMessage());

        }
        return ResponseEntity.internalServerError().body("Disculpe, estamos trabajando para solucionarlo ;)");
    }
}