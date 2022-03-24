package com.devsahamerlin.agency.repositories;

import com.devsahamerlin.agency.entities.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, UUID> {
    @Query("SELECT listingLimit FROM Dealer d WHERE d.id = :id")
    Integer getDealerLimit(UUID id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Dealer d SET d.listingLimit = :x WHERE d.id = :y ")
    void updateDealerLimit(@Param("x") int listingLimit, @Param("y") UUID id);

    @Query("SELECT count(id) FROM Listing l WHERE l.dealerId = :id ")
    Integer countDealerListing(UUID id);
}
