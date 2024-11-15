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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportService;

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") Date origin, @PathVariable("fechaFin") Date end) {
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

    @GetMapping("/activosVsMantenimiento")
     public ResponseEntity<?> getReporteMonopatinesActivosVsMantenidos(){
         try{
             ReporteMonopatinesEstado reporte = reportService.getReporteMonopatinesEstado();
             return ResponseEntity.status(200).body(reporte);
         } catch (Exception e) {
             return ResponseEntity.internalServerError().build();
         }
     }

     @GetMapping("/usoMonopatinesKm/{kilometros}") //reporte de uso de monopatines con un max de km
     public ResponseEntity<?> getReporteUsoMonopatinKm(@PathVariable("kilometros") float maxKm){
         try{
             ArrayList<ReporteMonopatinesUso> listaReportes = reportService.getReporteUsoMonopatinKm(maxKm);
             return ResponseEntity.status(200).body(listaReportes);
         } catch (Exception e) {
             return ResponseEntity.internalServerError().build();
         }
     }

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