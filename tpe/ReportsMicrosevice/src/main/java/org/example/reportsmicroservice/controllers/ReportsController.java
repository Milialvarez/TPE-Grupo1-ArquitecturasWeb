package org.example.reportsmicroservice.controllers;

import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.entities.ReporteMonopatinesEstado;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportService;

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end) {
            System.out.println("primer rep controller");
            ResponseEntity<?> reporteTotalFacturadoEntreFechas = reportService.getTotalBilled(origin, end);
            System.out.println("segundo rep controller");
                return ResponseEntity.status(HttpStatus.OK).body(reporteTotalFacturadoEntreFechas);
            }

            //ANDA
    @GetMapping("/activosVsMantenimiento")
     public ResponseEntity<?> getReporteMonopatinesActivosVsMantenidos(){
         try{
             System.out.println("hola repo controller");
             ReporteMonopatinesEstado reporte = reportService.getReporteMonopatinesEstado();
             System.out.println("se obtuvo bien");
             String response = reporte.toString();
             return ResponseEntity.status(200).body(Collections.singletonMap("report", response));
         } catch (Exception e) {
             System.out.println("catch de repo controller");
             return ResponseEntity.internalServerError().build();
         }
     }

     //VER
     @GetMapping("/usoMonopatinesKm/pausa/{pausa}") //reporte de uso de monopatines con un max de km
     public ResponseEntity<?> getReporteUsoMonopatinKm(@PathVariable("pausa") boolean pausa){
         try{
             System.out.println("hola repo controller");
              ArrayList<Object> reports = this.reportService.getReporteUsoMonopatinKm(pausa);
             System.out.println("god hasta acá");
              if(reports == null){
                  return ResponseEntity.status(404).build();
              }
              return ResponseEntity.status(200).body(reports);
         } catch (Exception e) {
             System.out.println("fuck controller");
             return ResponseEntity.internalServerError().build();
         }
     }
}