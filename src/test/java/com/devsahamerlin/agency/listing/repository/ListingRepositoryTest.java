package com.devsahamerlin.agency.listing.repository;

import com.devsahamerlin.agency.entities.Listing;
import com.devsahamerlin.agency.enums.ListingState;
import com.devsahamerlin.agency.repositories.ListingRepository;
import com.devsahamerlin.agency.restcontroller.ListingRestController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ListingRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ListingRestController.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ListingRepository listingRepository;


    @Test
    public void shouldFindNoListing_if_repositoryIsEmpty() {
        Iterable<Listing> listings = listingRepository.findAll();
        assertThat(listings).isEmpty();
    }

    @Test
    public void shouldSave_a_Listing() {
        Listing listing = listingRepository.save(new Listing(UUID.randomUUID(), UUID.randomUUID(), "Rav 4 2022",53000,new Date(), ListingState.DRAFT));
        assertThat(listing).hasFieldOrPropertyWithValue("price", 53000);
    }

    @Test
    public void shouldFind_all_DealerListing_and_State() {
        UUID dealer1Id = UUID.randomUUID();
        UUID dealer2Id = UUID.randomUUID();
        Pageable pageable = PageRequest.of(0, 3, Sort.by("createdAt").descending());

        Listing listing = listingRepository.save(new Listing(UUID.randomUUID(), dealer1Id, "Rav 4 2022",53000,new Date(), ListingState.DRAFT));
        entityManager.persist(listing);
        Listing listing2 = listingRepository.save(new Listing(UUID.randomUUID(), dealer2Id, "Nissan 4X4",53000,new Date(), ListingState.PUBLISHED));
        entityManager.persist(listing2);
        Listing listing3 = listingRepository.save(new Listing(UUID.randomUUID(), dealer1Id, "Avensis 2010",53000,new Date(), ListingState.DRAFT));
        entityManager.persist(listing3);

        Iterable<Listing>  listings = listingRepository.findByDealerIdAndState(dealer1Id,ListingState.DRAFT,pageable);
        assertThat(listings).hasSize(2).contains(listing, listing3);
    }

    @Test
    public void shouldUpdateListing_by_GivenIdAndListing() {
        UUID dealer1Id = UUID.randomUUID();

        Listing listing = listingRepository.save(new Listing(UUID.randomUUID(), dealer1Id, "Rav 4 2022",53000,new Date(), ListingState.DRAFT));
        entityManager.persist(listing);

        Listing listing2 = listingRepository.save(new Listing(UUID.randomUUID(), dealer1Id, "Avensis 2010",53000,new Date(), ListingState.DRAFT));
        entityManager.persist(listing2);

        logger.info("Before update: {}, {}",listing.getVehicle(), listing.getPrice());

        Listing listing1 = listingRepository.getById(listing.getId());
        listing1.setVehicle("Rav 4 2023");
        listing1.setPrice(60000);
        listingRepository.save(listing1);

        Listing checkListingUpdated = listingRepository.getById(listing.getId());

        logger.info("After update: {}, {}",checkListingUpdated.getVehicle(), checkListingUpdated.getPrice());

        assertThat(checkListingUpdated.getId()).isEqualTo(listing.getId());
        assertThat(checkListingUpdated.getVehicle()).isEqualTo(listing.getVehicle());
        assertThat(checkListingUpdated.getPrice()).isEqualTo(listing.getPrice());

    }

    @Test
    public void shouldPublishAListing_by_GivenStateAndId() {
        UUID dealer1Id = UUID.randomUUID();

        Listing listing = listingRepository.save(new Listing(UUID.randomUUID(), dealer1Id, "Rav 4 2022",53000,new Date(), ListingState.DRAFT));
        entityManager.persist(listing);

        logger.info("Before: {}, {}",listing.getVehicle(), listing.getState());

        listingRepository.publishListing(ListingState.PUBLISHED, listing.getId());

        Listing checkListingState = listingRepository.getById(listing.getId());

        logger.info("After: {}, {}",checkListingState.getVehicle(), checkListingState.getState());

        assertThat(checkListingState.getState()).isEqualTo(ListingState.PUBLISHED);

    }

    @Test
    public void shouldUnPublishAListing_by_GivenId() {
        UUID dealer1Id = UUID.randomUUID();

        Listing listing = listingRepository.save(new Listing(UUID.randomUUID(), dealer1Id, "Rav 4 2022",53000,new Date(), ListingState.DRAFT));
        entityManager.persist(listing);

        logger.info("Before publish: {}, {}",listing.getVehicle(), listing.getState());

        listingRepository.publishListing(ListingState.PUBLISHED, listing.getId());

        Listing checkListingState = listingRepository.getById(listing.getId());

        logger.info("After publish: {}, {}",checkListingState.getVehicle(), checkListingState.getState());

        listingRepository.publishListing(ListingState.DRAFT, listing.getId());

        Listing checkListingDefiniteState = listingRepository.getById(listing.getId());

        logger.info("Finally state: {}, {}",checkListingDefiniteState.getVehicle(), checkListingDefiniteState.getState());

        assertThat(checkListingDefiniteState.getState()).isEqualTo(ListingState.DRAFT);
    }

}
