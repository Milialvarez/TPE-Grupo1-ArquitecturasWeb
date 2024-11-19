package org.example.reportsmicroservice.services;

import feign.FeignException;
import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.entities.ReporteMonopatinesEstado;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.feignClients.BillingFeignClient;
import org.example.reportsmicroservice.feignClients.MonopatinFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReportsService {
    @Autowired
    BillingFeignClient billingFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;


    public ResponseEntity<?> getTotalBilled(LocalDate origin, LocalDate end){
        try {
            ResponseEntity<?> totalBilled = this.billingFeignClient.getTotalBilled(origin, end);
            Double total = (Double) totalBilled.getBody();
            return ResponseEntity.status(200).body(new ReporteFacturacion("Reporte de facturacion", "Ganancias hechas en los viajes entre fechas.", total, origin, end));
        } catch (Exception e) {
            return null;
        }
    }

    public ReporteMonopatinesEstado getReporteMonopatinesEstado(){
        ResponseEntity<?> responseActivos = this.monopatinFeignClient.getMonopatinesActivos();
        ResponseEntity<?> responseMantenim = this.monopatinFeignClient.getMonopatinesEnMantenimiento();
        try {
            if (responseActivos.getStatusCode().is2xxSuccessful() && responseMantenim.getStatusCode().is2xxSuccessful()){
                return new ReporteMonopatinesEstado((Integer) responseActivos.getBody(), (Integer) responseMantenim.getBody());
            } else {
                // Manejar una respuesta no exitosa
                throw new IllegalStateException("Error al llamar al otro servicio");
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }


    public ArrayList<ReporteMonopatinesUso> getReporteUsoMonopatinPorTiempo(float maxT, boolean p){
        ResponseEntity<?> responsePorTiempo = this.monopatinFeignClient.getMonopatinesPorTiempo(maxT, p);
        try {
            if (responsePorTiempo.getStatusCode().is2xxSuccessful()){
                ArrayList<ReporteMonopatinesUso> listaReportes = new ArrayList<>();
                ArrayList<Object[]> listaMonopatinesPorT = (ArrayList<Object[]>) responsePorTiempo.getBody();
                for (Object[] monop : listaMonopatinesPorT){
                    Long id = (Long) monop[0];
                    Float kmRecorridos = (Float) monop[1];
                    Float tiempoUso = (Float) monop[2];
                    Float tiempoUsoConPausa = (Float) monop[3];
                    ReporteMonopatinesUso r = new ReporteMonopatinesUso(id, kmRecorridos, tiempoUso, tiempoUsoConPausa);
                    listaReportes.add(r);
                }
                return listaReportes;
            } else {
                // Manejar una respuesta no exitosa
                throw new IllegalStateException("Error al llamar al otro servicio");
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }

    public ArrayList<ReporteMonopatinesUso> getReporteUsoMonopatinKm(float maxKm){
        ResponseEntity<?> responsePorKm = this.monopatinFeignClient.getMonopatinesPorKm(maxKm);
//        ResponseEntity<?> responsePorTiempoConPausa = this.monopatinFeignClient.getMonopatinesPorTiempoConPausa(maxKm);

        try {
            if (responsePorKm.getStatusCode().is2xxSuccessful()){
                ArrayList<ReporteMonopatinesUso> listaReportes = new ArrayList<>();
                ArrayList<Object[]> listaMonopatinesPorKm = (ArrayList<Object[]>) responsePorKm.getBody();
                for (Object[] monop : listaMonopatinesPorKm){
                    Long id = (Long) monop[0];
                    Float kmRecorridos = (Float) monop[1];
                    Float tiempoUso = (Float) monop[2];
                    Float tiempoUsoConPausa = (Float) monop[3];
                    ReporteMonopatinesUso r = new ReporteMonopatinesUso(id, kmRecorridos, tiempoUso, tiempoUsoConPausa);
                    listaReportes.add(r);
                }
                return listaReportes;
            } else {
                // Manejar una respuesta no exitosa
                throw new IllegalStateException("Error al llamar al otro servicio");
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }
}