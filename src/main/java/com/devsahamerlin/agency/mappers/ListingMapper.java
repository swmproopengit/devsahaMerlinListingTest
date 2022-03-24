package com.devsahamerlin.agency.mappers;

import com.devsahamerlin.agency.dto.ListingRequestDto;
import com.devsahamerlin.agency.dto.ListingResponseDto;
import com.devsahamerlin.agency.entities.Listing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ListingMapper {
    ListingResponseDto listingToListingResponseDTO(Listing listing);
    Listing listingRequestDTOToListing(ListingRequestDto listingRequestDto);
}
