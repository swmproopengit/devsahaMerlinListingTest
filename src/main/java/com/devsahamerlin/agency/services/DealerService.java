package com.devsahamerlin.agency.services;

import com.devsahamerlin.agency.dto.DealerRequestDto;
import com.devsahamerlin.agency.dto.DealerResponseDto;

import java.util.UUID;

public interface DealerService {
    DealerResponseDto addDealer(DealerRequestDto dealerRequestDto);
    DealerResponseDto getDealerById(UUID id);
    String updateDealerLimit(int listingLimit, UUID id);
    Integer getDealerLimit(UUID id);
    Integer countDealerListing(UUID id);
}
