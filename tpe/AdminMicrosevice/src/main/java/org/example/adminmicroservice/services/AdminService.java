package org.example.adminmicroservice.services;

import feign.FeignException;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.feignClients.BillingFeignClient;
import org.example.adminmicroservice.feignClients.MonopatinFeignClient;
import org.example.adminmicroservice.feignClients.ReportsFeignClient;
import org.example.adminmicroservice.feignClients.AccountsFeignClient;
import org.example.adminmicroservice.models.Account;
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

@Service
public class AdminService {
    @Autowired
    RestTemplate restTemplate;
    AccountsFeignClient accountsFeignClient;
    MonopatinFeignClient monopatinFeignClient;
    ReportsFeignClient reportsFeignClient;
    BillingFeignClient billingFeignClient;

    public List getAdmins() {
        return restTemplate.getForObject("http://localhost:8001/users", List.class);
    }

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        ResponseEntity<?> monopatins = this.monopatinFeignClient.getMonopatinesPorViajesPorAnio(anio, xViajes);
        return (List<Monopatin>) monopatins;
    }

    @PutMapping("/null")
    public ResponseEntity<?> anullateAccount(@RequestBody Account ac){
        return this.accountsFeignClient.anullateAccount(ac);
    }

    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end){
        try {
            return this.reportsFeignClient.getTotalBilled(origin, end);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Object setNewBill(Date fecha, float pFijo, float pExtra){
        ResponseEntity<?> response = this.billingFeignClient.setNewBill(fecha, pFijo, pExtra);
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
