package org.example.billingmicroservice.controllers;

import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController{
    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<?> getAllBills(){
        ArrayList<Bill> bills = (ArrayList<Bill>) billService.getAll();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end){
        try {
            double reporteTotalFacturadoEntreFechas = billService.getTotalBilled(origin, end);
            return ResponseEntity.ok(reporteTotalFacturadoEntreFechas);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyBill(@RequestBody Bill bill){
        try{
            Bill b = billService.modifyBill(bill);
            return ResponseEntity.ok(b);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }





}