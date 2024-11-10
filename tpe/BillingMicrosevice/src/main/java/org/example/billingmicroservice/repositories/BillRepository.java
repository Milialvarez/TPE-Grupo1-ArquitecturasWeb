package org.example.billingmicroservice.repositories;

import org.example.billingmicroservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {


    void setNewPrice(Long id, double price);
}