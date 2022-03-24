package com.devsahamerlin.agency.repositories;

import com.devsahamerlin.agency.entities.Listing;
import com.devsahamerlin.agency.enums.ListingState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

    @Query("SELECT l FROM Listing l WHERE l.dealerId = :dealerId AND l.state = :state")
    Page<Listing> findByDealerIdAndState(UUID dealerId, ListingState state, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Listing l SET l.state = :state WHERE l.id = :id ")
    void publishListing(ListingState state, UUID id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Listing l SET l.state = :state WHERE l.id = :id ")
    void unPublishListing(ListingState state, UUID id);
}
