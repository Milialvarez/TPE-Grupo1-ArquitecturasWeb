package org.example.reportsmicroservice.controllers;

import org.example.adminmicroservice.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportService;

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen")LocalDate origin,@PathVariable("fechaFin") LocalDate end){
        try {
            List<Object[]> reporteTotalFacturadoEntreFechas = reportService.getTotalBilled(origin, end);
            return ResponseEntity.ok(reporteTotalFacturadoEntreFechas);
        } catch () {

        }

    }
}