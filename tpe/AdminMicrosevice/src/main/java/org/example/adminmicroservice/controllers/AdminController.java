package org.example.adminmicroservice.controllers;

import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.Monopatin;
import org.example.adminmicroservice.models.User;
import org.example.adminmicroservice.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
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
            List<Object[]> reporteTotalFacturadoEntreFechas = (List<Object[]>) adminService.getTotalBilled(origin, end);
            return ResponseEntity.ok(reporteTotalFacturadoEntreFechas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping()
    public ResponseEntity<?> setNewBill(@RequestBody BillDTO tarifaDTO){
        try {
            Date fechaVigencia = tarifaDTO.getFechaInicioFacturacionNueva();
            float pFijo = tarifaDTO.getPrecioFijo();
            float pExtra = tarifaDTO.getPrecioExtra();
            Object result = this.adminService.setNewBill(fechaVigencia, pFijo, pExtra);
            if (result != null) return ResponseEntity.status(201).body("Tarifa agregada con exito");
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un problema con el payload");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}