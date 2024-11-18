package org.example.adminmicroservice.controllers;

import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.Monopatin;
import org.example.adminmicroservice.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body("Todo ok andando en admin");
    }

    @GetMapping("/role/{role}") //role must be either: [usuario, admin, mantenimiento]
    public ResponseEntity<List<Object>> getUsersByRole(@PathVariable("role") String r) {
        List<Object> users = adminService.getUsersByRole(r);
        return ResponseEntity.status(200).body(users);
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

    @GetMapping("/activosVsMantenimiento")
    public ResponseEntity<?> getReporteMonopatinesSegunEstado(){
        try{
            Object reporte = adminService.getReporteMonopatinesSegunEstado();
            return ResponseEntity.status(200).body(reporte.toString());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
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
            else return ResponseEntity.status(400).body("Hubo un problema con el payload");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}