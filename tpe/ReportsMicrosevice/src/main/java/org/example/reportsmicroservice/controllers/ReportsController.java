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
     @GetMapping("/usoMonopatinesKm/{kilometros}") //reporte de uso de monopatines con un max de km
     public ResponseEntity<?> getReporteUsoMonopatinKm(@PathVariable("kilometros") float maxKm){
         try{
             ArrayList<ReporteMonopatinesUso> listaReportes = reportService.getReporteUsoMonopatinKm(maxKm);
             return ResponseEntity.status(200).body(listaReportes);
         } catch (Exception e) {
             return ResponseEntity.internalServerError().build();
         }
     }

     //VER
    @GetMapping({"/usoMonopatinesTiempo/{tiempo}/{pausa}"}) //reporte de uso de monopatines con un max de tiempo y un isPausa
     public ResponseEntity<?> getReporteUsoMonopatinPausa(@PathVariable(value = "tiempo") float tiempo, @PathVariable(value = "pausa", required = false) boolean p){
        try {
            ArrayList<ReporteMonopatinesUso> listaReportes = reportService.getReporteUsoMonopatinPorTiempo(tiempo, p);
            return ResponseEntity.status(200).body(listaReportes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
     }
}