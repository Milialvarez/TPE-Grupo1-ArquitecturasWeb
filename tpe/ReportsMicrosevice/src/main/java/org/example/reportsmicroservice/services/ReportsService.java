package org.example.reportsmicroservice.services;

import feign.FeignException;
import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.entities.ReporteMonopatinesEstado;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.feignClients.BillingFeignClient;
import org.example.reportsmicroservice.feignClients.MonopatinFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportsService {
    BillingFeignClient billingFeignClient;
    MonopatinFeignClient monopatinFeignClient;


    public Optional<ReporteFacturacion> getTotalBilled(@PathVariable("fechaOrigen") Date origin, @PathVariable("fechaFin") Date end){
        try {
            double totalBilled = (Double) this.billingFeignClient.getTotalBilled(origin, end).getBody();
            return Optional.of(new ReporteFacturacion("Reporte de facturacion", "Ganancias hechas en los viajes entre fechas.", totalBilled, origin, end));
        } catch (Exception e) {
            return Optional.empty();
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