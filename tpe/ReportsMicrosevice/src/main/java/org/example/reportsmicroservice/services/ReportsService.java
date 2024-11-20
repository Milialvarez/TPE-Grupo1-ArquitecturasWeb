package org.example.reportsmicroservice.services;

import feign.FeignException;
import org.example.reportsmicroservice.dtos.MonopatinKmDTO;
import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.entities.ReporteMonopatinesEstado;
import org.example.reportsmicroservice.entities.ReporteMonopatinesSinPausa;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.feignClients.BillingFeignClient;
import org.example.reportsmicroservice.feignClients.MonopatinFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReportsService {
    @Autowired
    BillingFeignClient billingFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;


    public ReporteFacturacion getTotalBilled(LocalDate origin, LocalDate end){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String originFormatted = origin.format(formatter);
            String endFormatted = end.format(formatter);

            ResponseEntity<?> response = this.billingFeignClient.getTotalBilled(originFormatted, endFormatted);
            Double totalBilled = (Double) response.getBody();
            return new ReporteFacturacion("Reporte de facturacion", "Ganancias hechas en los viajes entre fechas.", totalBilled, origin, end);
        } catch (Exception e) {
            return null;
        }
    }

    public ReporteMonopatinesEstado getReporteMonopatinesEstado(){
        ResponseEntity<?> responseActivos = this.monopatinFeignClient.getMonopatinesActivos();
        ResponseEntity<?> responseMantenim = this.monopatinFeignClient.getMonopatinesEnMantenimiento();
        try {
            if (Objects.equals(responseActivos.getStatusCode().toString(), "200 OK") && Objects.equals(responseMantenim.getStatusCode().toString(), "200 OK")){
                ArrayList<Object> mantenimiento = (ArrayList<Object>) responseMantenim.getBody();
                ArrayList<Object> activos = (ArrayList<Object>) responseActivos.getBody();
                System.out.println("if de repo service");
                System.out.println(mantenimiento);
                System.out.println(activos);
                assert activos != null;
                assert mantenimiento != null;
                System.out.println(activos.size());
                System.out.println(mantenimiento.size());
                return new ReporteMonopatinesEstado(activos.size(), mantenimiento.size());
            } else {
                System.out.println("else del repo service");
                // Manejar una respuesta no exitosa
                throw new IllegalStateException("Error al llamar al otro servicio");
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }

    public ArrayList<Object> getReporteUsoMonopatinKm(boolean pausa){
        try {
            System.out.println("hola service");
            ResponseEntity<ArrayList<MonopatinKmDTO>> result = this.monopatinFeignClient.getMonopatinesPorKM(pausa);
            System.out.println(result.getBody());
            System.out.println("no se rompió");
            if (Objects.equals(result.getStatusCode().toString(), "200 OK")){
                System.out.println("entré al if");
                ArrayList<MonopatinKmDTO> listaMonopatinesPorKm = (ArrayList<MonopatinKmDTO>) result.getBody();
                System.out.println(listaMonopatinesPorKm);
                ArrayList<Object> listaReportes = new ArrayList<>();
                if(pausa){
                    System.out.println("entré al segundo if");
                    assert listaMonopatinesPorKm != null;
                    System.out.println("acá rompe");
                    for(MonopatinKmDTO monopatinKmDTO : listaMonopatinesPorKm) {
                        System.out.println(monopatinKmDTO + "            " + monopatinKmDTO.getId());
                        Long id = monopatinKmDTO.getId();
                        Float kms = monopatinKmDTO.getKmRecorridos();
                        Integer tiempo = monopatinKmDTO.getTiempoUso();
                        Integer pausas = monopatinKmDTO.getTiempoPausas();
                        listaReportes.add(new ReporteMonopatinesUso(id, kms, tiempo, pausas));
                        System.out.println("ya se rompió no?");
                    }
                    return listaReportes;
                }else{
                    System.out.println("sout else de es sin pausa");
                    assert listaMonopatinesPorKm != null;
                    for(MonopatinKmDTO monopatinKmDTO : listaMonopatinesPorKm){
                        Long id = monopatinKmDTO.getId();
                        Float kms = monopatinKmDTO.getKmRecorridos();
                        Integer tiempo = monopatinKmDTO.getTiempoUso();
                        listaReportes.add(new ReporteMonopatinesSinPausa(id, kms, tiempo));
                    }
                    return listaReportes;
                }
            } else {
                System.out.println("mal ahi, primer else");
                return null;
            }
        } catch (FeignException.FeignClientException exception){
            System.out.println("fuck service");
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }
}