package com.devsahamerlin.agency.services;

import com.devsahamerlin.agency.dto.ListingRequestDto;
import com.devsahamerlin.agency.dto.ListingResponseDto;
import com.devsahamerlin.agency.enums.ListingState;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ListingService {
    ListingResponseDto createListing(ListingRequestDto listingRequestDto);
    ListingResponseDto updateListing(UUID id, ListingRequestDto listingRequestDto);
    List<ListingResponseDto> getAllDealerListingByGivenState(UUID dealerId, ListingState state, Pageable pageable);
    Boolean publishListing(UUID id);
    Boolean unPublishListing(UUID id);
}
