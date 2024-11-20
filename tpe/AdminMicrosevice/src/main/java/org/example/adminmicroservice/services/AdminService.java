package org.example.adminmicroservice.services;

import feign.FeignException;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.feignClients.*;
import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("AdminService")
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

    public ResponseEntity<?> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        return this.monopatinFeignClient.getMonopatinesPorViajesPorAnio(anio, xViajes);
    }

    public ResponseEntity<?> anullateAccount(Integer id){
        return this.accountsFeignClient.anullateAccount(id);
    }

    public ResponseEntity<?> getTotalBilled(LocalDate origin, LocalDate end){
        return this.reportsFeignClient.getTotalBilled(origin, end);
    }

    public ResponseEntity<?> getUsersByRole(String r){
        return this.usersFeignClient.getUsersByRole(r);
    }

    public ResponseEntity<?> getReporteMonopatinesSegunEstado(){
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
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }
}
