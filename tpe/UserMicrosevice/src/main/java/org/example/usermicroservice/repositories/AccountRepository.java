package org.example.usermicroservice.repositories;

import org.example.usermicroservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Cuenta c SET c.anullated = :s WHERE a.id = :id")
    Account setAccountAnullated(@Param("id") Long id, @Param("state") state);
}
