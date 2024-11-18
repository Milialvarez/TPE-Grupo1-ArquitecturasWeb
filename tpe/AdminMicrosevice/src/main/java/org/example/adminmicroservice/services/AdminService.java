package org.example.adminmicroservice.services;

import feign.FeignException;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.feignClients.*;
import org.example.adminmicroservice.models.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    UserFeignClient usersFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;
    @Autowired
    ReportsFeignClient reportsFeignClient;
    @Autowired
    BillingFeignClient billingFeignClient;
    @Autowired
    AccountsFeignClient accountsFeignClient;

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        ResponseEntity<?> monopatins = this.monopatinFeignClient.getMonopatinesPorViajesPorAnio(anio, xViajes);
        return (List<Monopatin>) monopatins;
    }

    public ResponseEntity<?> anullateAccount(Integer id){
        ResponseEntity<?> result = this.accountsFeignClient.anullateAccount(id);
        return result;
    }

    public Optional<Object[]> getTotalBilled(LocalDate origin, LocalDate end){
        Optional<Object[]> reporte = (Optional<Object[]>) this.reportsFeignClient.getTotalBilled(origin, end).getBody();
        return reporte;
    }

    public List<Object> getUsersByRole(String r){
        return (List<Object>) this.usersFeignClient.getUsersByRole(r);
    }

    public Object getReporteMonopatinesSegunEstado(){
        return this.reportsFeignClient.getReporteMonopatinesActivosVsMantenidos();
    }

    public ResponseEntity<?> setNewBill(BillDTO b){
        ResponseEntity<?> response = this.billingFeignClient.setNewBill(b);
        try {
            if (Objects.equals(response.getStatusCode().toString(), "201 CREATED")){
                return response;
            } else{
                return ResponseEntity.status(404).body("Wasn't possible to set a new bill, wrong date");
            }
        } catch (FeignException.FeignClientException exception){
            System.out.println("catch de admin service");
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }
}
