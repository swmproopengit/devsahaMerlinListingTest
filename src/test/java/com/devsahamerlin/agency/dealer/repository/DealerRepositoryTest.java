package com.devsahamerlin.agency.dealer.repository;

import com.devsahamerlin.agency.entities.Dealer;
import com.devsahamerlin.agency.repositories.DealerRepository;
import com.devsahamerlin.agency.restcontroller.ListingRestController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DealerRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ListingRestController.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DealerRepository dealerRepository;


    @Test
    public void shouldFindNoDealer_if_repositoryIsEmpty() {
        Iterable<Dealer> dealers = dealerRepository.findAll();
        assertThat(dealers).isEmpty();
    }

    @Test
    public void shouldSave_a_Dealer() {
        Dealer dealer = dealerRepository.save(new Dealer(UUID.randomUUID(), "Toyota", 10));
        assertThat(dealer).hasFieldOrPropertyWithValue("id", dealer.getId());
        assertThat(dealer).hasFieldOrPropertyWithValue("name", "Toyota");
        assertThat(dealer).hasFieldOrPropertyWithValue("listingLimit", 10);
    }

    @Test
    public void shouldFind_all_Dealer() {
        Dealer dealer = dealerRepository.save(new Dealer(UUID.randomUUID(), "Toyota", 10));
        entityManager.persist(dealer);
        Dealer dealer2 = dealerRepository.save(new Dealer(UUID.randomUUID(), "Kia", 10));
        entityManager.persist(dealer2);
        Dealer dealer3 = dealerRepository.save(new Dealer(UUID.randomUUID(), "Nissan", 10));
        entityManager.persist(dealer3);
        Iterable<Dealer> tutorials = dealerRepository.findAll();
        assertThat(tutorials).hasSize(3).contains(dealer, dealer2, dealer3);
    }

    @Test
    public void shouldFindDealer_by_Id() {
        Dealer dealer = dealerRepository.save(new Dealer(UUID.randomUUID(), "Toyota", 10));
        entityManager.persist(dealer);
        Dealer dealer2 = dealerRepository.save(new Dealer(UUID.randomUUID(), "Kia", 10));
        entityManager.persist(dealer2);
        Dealer found = dealerRepository.findById(dealer2.getId()).get();
        assertThat(found).isEqualTo(dealer2);
    }

    @Test
    public void shouldUpdateDealerLimit_by_givenIdAndLimit() {

        Dealer dealer = dealerRepository.save(new Dealer(UUID.randomUUID(), "Toyota", 10));
        entityManager.persist(dealer);

        logger.info("Before update: {}",dealer.getListingLimit());

        dealerRepository.updateDealerLimit(20,dealer.getId());

        Dealer found = dealerRepository.getById(dealer.getId());

        logger.info("After update: {}",found.getListingLimit());

        assertThat(found.getListingLimit()).isEqualTo(20);
    }

    @Test
    public void shouldGetDealerLimit_by_givenId() {
        Dealer dealer = dealerRepository.save(new Dealer(UUID.randomUUID(), "Toyota", 10));
        entityManager.persist(dealer);
        Integer found = dealerRepository.getDealerLimit(dealer.getId());
        assertThat(found).isEqualTo(dealer.getListingLimit());
    }


}
