package org.example.reportsmicroservice.controllers;

import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportService;

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end) {
        try {
            Optional<ReporteFacturacion> reporteTotalFacturadoEntreFechas = reportService.getTotalBilled(origin, end);
            if (reporteTotalFacturadoEntreFechas.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(reporteTotalFacturadoEntreFechas);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos invalidos o no existe un reporte.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error al solicitar el reporte.");
        }

    }
}