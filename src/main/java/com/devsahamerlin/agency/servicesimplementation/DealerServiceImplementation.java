package com.devsahamerlin.agency.servicesimplementation;

import com.devsahamerlin.agency.dto.DealerRequestDto;
import com.devsahamerlin.agency.dto.DealerResponseDto;
import com.devsahamerlin.agency.entities.Dealer;
import com.devsahamerlin.agency.exceptions.DealerNotFoundException;
import com.devsahamerlin.agency.mappers.DealerMapper;
import com.devsahamerlin.agency.repositories.DealerRepository;
import com.devsahamerlin.agency.services.DealerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DealerServiceImplementation implements DealerService {

    private static final Logger logger = LoggerFactory.getLogger(DealerServiceImplementation.class);

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private DealerMapper dealerMapper;

    @Override
    public DealerResponseDto addDealer(DealerRequestDto dealerRequestDto) {
        logger.info("Create new Dealer {} ", dealerRequestDto);
        Dealer dealer = dealerMapper.dealerRequestDTOToDealer(dealerRequestDto);

        dealer.setId(UUID.randomUUID());
        Dealer savedDealer = dealerRepository.save(dealer);

        return dealerMapper.dealerToDealerResponseDTO(savedDealer);
    }

    @Override
    public DealerResponseDto getDealerById(UUID id) {
        Optional<Dealer> dealer = dealerRepository.findById(id);

        dealerRepository.findById(id)
                .orElseThrow(() -> new DealerNotFoundException(id));

        return dealerMapper.dealerToDealerResponseDTO(dealer.get());

    }

    @Override
    public String updateDealerLimit(int listingLimit, UUID id) {
        dealerRepository.findById(id)
                .orElseThrow(() -> new DealerNotFoundException(id));

        logger.info("Update dealer limit by {} ", id);
        dealerRepository.updateDealerLimit(listingLimit, id);
        return "Updated successfully";


    }

    @Override
    public Integer getDealerLimit(UUID id) {

        dealerRepository.findById(id)
                .orElseThrow(() -> new DealerNotFoundException(id));

            logger.info("Fetch dealer limit by {} ", id);
            return dealerRepository.getDealerLimit(id);
    }

    @Override
    public Integer countDealerListing(UUID id) {

        dealerRepository.findById(id)
                .orElseThrow(() -> new DealerNotFoundException(id));

           logger.info("Count dealer limit by {} ", id);
           return dealerRepository.countDealerListing(id);

    }



}
