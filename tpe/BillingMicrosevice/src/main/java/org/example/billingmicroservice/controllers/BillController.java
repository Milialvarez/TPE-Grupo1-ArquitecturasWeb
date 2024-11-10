package org.example.billingmicroservice.controllers;

import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/bills")
public class BillController{
    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<?> getAllBills(){
        try{
            ArrayList<Bill> bills =  billService.getAllBills();
            return ResponseEntity.ok().body(bills);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public ResponseEntity<?> getTotalBilled(LocalDate origin, LocalDate end){
        try{
            double total = billService.getTotalBilled(origin,end);
            return ResponseEntity.ok().body(total);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}