package org.example.billingmicroservice.services;


import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.feignClient.MonopatinFeignClient;
import org.example.billingmicroservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    ViajeFeignClient viajeFeignClient;

    public List<Bill> getAll() {
        return billRepository.findAll();
    }


    public Bill save(Bill b) {
        this.billRepository.save(b);
        return b;
    }

    public boolean delete(Bill b) {
        billRepository.delete(b);
        return true;
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    public Bill setNewPrice(Long id, double price) {
        Bill b = this.getBillById(id);
        if (b != null) this.billRepository.setNewPrice(id, price);
        return b;
    }

    public double getTotalBilled(LocalDate origin, LocalDate end){
        List<?> viajes = (List<?>) this.viajeFeignClient.getViajesBetween(origin, end);
        double total = 0.0;
        for(Object v : viajes){
//            total += (Double) v.get
        }
        return 0.0;
    }

    public Bill modifyBill(Bill bill) {
        this.billRepository.modify(bill.getId(), bill.getPrice(), bill.getAdditionalPrice());
        return bill;
    }
}