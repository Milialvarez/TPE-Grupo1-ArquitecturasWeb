package org.example.billingmicroservice.repositories;

import org.example.billingmicroservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {

    @Modifying
    @Query("UPDATE Bill b SET b.price =:price, b.additionalPrice=:additionalPrice WHERE b.id =:id")
    void modify(Long id, double price, double additionalPrice);

    @Modifying
    @Query("UPDATE Bill b SET b.price =:price WHERE b.id =:id")
    void setNewPrice(Long id, double price);

    @Query("SELECT b.fecha FROM Bill b WHERE b.fecha = (SELECT MAX(b2.fecha) FROM Bill b2)")
    LocalDate getLastOne();
}