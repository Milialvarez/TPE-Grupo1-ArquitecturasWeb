package org.example.usermicroservice.repositories;

import jakarta.transaction.Transactional;
import org.example.usermicroservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Account c SET c.anullated = :s WHERE c.id = :id")
    Account setAccountAnullated(@Param("id") Long id);
}
