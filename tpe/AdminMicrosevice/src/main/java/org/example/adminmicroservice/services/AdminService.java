package org.example.adminmicroservice.services;

import feign.FeignException;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.feignClients.*;
import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.Bill;
import org.example.adminmicroservice.models.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
        Long id_acc = id.longValue();
        int result = this.accountsFeignClient.anullateAccount(id_acc);
        if(result <0){
            return ResponseEntity.status(404).body("id not found");
        } else{
            return ResponseEntity.status(200).body(id);
        }
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

    public Object setNewBill(Bill b){
        ResponseEntity<?> response = this.billingFeignClient.setNewBill(b);
        try {
            if (response.getStatusCode().is2xxSuccessful()){
                Object bill = response.getBody();
                return bill;
            } else {
                // Manejar una respuesta no exitosa
                throw new IllegalStateException("Error al llamar al otro servicio");
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }
}
