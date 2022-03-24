package com.devsahamerlin.agency.servicesimplementation;

import com.devsahamerlin.agency.dto.ListingRequestDto;
import com.devsahamerlin.agency.dto.ListingResponseDto;
import com.devsahamerlin.agency.entities.Listing;
import com.devsahamerlin.agency.enums.ListingState;
import com.devsahamerlin.agency.exceptions.DealerLimitException;
import com.devsahamerlin.agency.exceptions.DealerNotFoundException;
import com.devsahamerlin.agency.exceptions.ListingNotFoundException;
import com.devsahamerlin.agency.exceptions.UnKnowException;
import com.devsahamerlin.agency.mappers.ListingMapper;
import com.devsahamerlin.agency.repositories.DealerRepository;
import com.devsahamerlin.agency.repositories.ListingRepository;
import com.devsahamerlin.agency.services.DealerService;
import com.devsahamerlin.agency.services.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListingServiceImplementation implements ListingService {

    private static final Logger logger = LoggerFactory.getLogger(ListingServiceImplementation.class);


    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ListingMapper listingMapper;

    @Autowired
    private DealerService dealerService;

    @Override
    public ListingResponseDto createListing(ListingRequestDto listingRequestDto) {

        dealerRepository.findById(listingRequestDto.getDealerId())
                .orElseThrow(() -> new DealerNotFoundException(listingRequestDto.getDealerId()));


        Integer dealerLimit = dealerService.getDealerLimit(listingRequestDto.getDealerId());
        Integer dealerListingNumber = dealerService.countDealerListing(listingRequestDto.getDealerId());

        if (dealerListingNumber != null ){
            logger.info("Already saved {}/{} listing", dealerListingNumber, dealerLimit);
            if(dealerListingNumber>=dealerLimit){
                throw new DealerLimitException(listingRequestDto.getDealerId(), dealerListingNumber, dealerLimit);
            }else{
                Listing listing = listingMapper.listingRequestDTOToListing(listingRequestDto);
                listing.setId(UUID.randomUUID());
                listing.setCreatedAt(new Date());
                listing.setState(ListingState.DRAFT);

                Listing savedListing = listingRepository.save(listing);

                return listingMapper.listingToListingResponseDTO(savedListing);
            }
        }else {
            throw new DealerLimitException(listingRequestDto.getDealerId(), dealerListingNumber, dealerLimit);
        }
    }

    @Override
    public ListingResponseDto updateListing(UUID id, ListingRequestDto listingRequestDto) {

        dealerRepository.findById(listingRequestDto.getDealerId())
                .orElseThrow(() -> new DealerNotFoundException(listingRequestDto.getDealerId()));

        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()){
            Listing listingU = listing.get();
            listingU.setVehicle(listingRequestDto.getVehicle());
            listingU.setPrice(listingRequestDto.getPrice());

            Listing updatedListing = listingRepository.save(listingU);

            logger.info("Listing {} updated by {} ", id, listingRequestDto.getDealerId());

            return listingMapper.listingToListingResponseDTO(updatedListing);
        }else {
            logger.error("Listing {} updated by {} failed ", id, listingRequestDto.getDealerId());
            throw new ListingNotFoundException(id);
        }
    }

    @Override
    public List<ListingResponseDto> getAllDealerListingByGivenState(UUID dealerId, ListingState state, Pageable pageable) {

        dealerRepository.findById(dealerId)
                .orElseThrow(() -> new DealerNotFoundException(dealerId));

        Page<Listing> listingList = listingRepository.findByDealerIdAndState(dealerId, state, pageable);
        try {
            if(listingList.isEmpty()){
                logger.error("Get all {} listing by {} failed ", state, dealerId);
                return Collections.emptyList();
            }else {
                logger.info("Get all {} listing by {} ", state, dealerId);
                return listingList.stream()
                        .map(product -> listingMapper.listingToListingResponseDTO(product))
                        .collect(Collectors.toList());
            }
        }catch (Exception e){
            throw new UnKnowException();
        }


    }

    @Override
    public Boolean publishListing(UUID id) {

        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()){
            logger.info("Listing {} published ", id);
            listingRepository.publishListing(ListingState.PUBLISHED, id);
            return true;
        }else {
            logger.error("Listing {} publish failed ", id);
            throw new ListingNotFoundException(id);
        }
    }

    @Override
    public Boolean unPublishListing(UUID id) {

        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()){
            logger.info("Listing {} unPublished ", id);
            listingRepository.unPublishListing(ListingState.DRAFT, id);
            return true;
        }else {
            logger.error("Listing {} unPublished failed ", id);
            throw new ListingNotFoundException(id);
        }
    }
}
