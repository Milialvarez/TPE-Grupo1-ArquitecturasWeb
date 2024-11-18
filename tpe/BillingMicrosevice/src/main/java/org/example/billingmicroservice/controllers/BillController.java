package org.example.billingmicroservice.controllers;

import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController{
    @Autowired
    private BillService billService;

    @GetMapping //Andando
    public ResponseEntity<?> getAllBills(){
        ArrayList<Bill> bills = (ArrayList<Bill>) billService.getAll();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end){
        try {
            double reporteTotalFacturadoEntreFechas = billService.getTotalBilled(origin, end);
            if (reporteTotalFacturadoEntreFechas!= -1)
                return ResponseEntity.ok(reporteTotalFacturadoEntreFechas);
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro una facturacion para las fechas solicitadas.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error al realizar la solicitud.");
        }
    }

    @PostMapping()
    public ResponseEntity<?> setNewBill(@RequestBody Bill bill){ //Definir precio y 3 F
        try{
            Bill b = billService.setNewBill(bill.getFecha(), bill.getPrice(), bill.getAdditionalPrice());
            return ResponseEntity.status(201).body(b);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}