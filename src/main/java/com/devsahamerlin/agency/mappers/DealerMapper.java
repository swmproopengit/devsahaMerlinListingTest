package com.devsahamerlin.agency.mappers;

import com.devsahamerlin.agency.dto.DealerRequestDto;
import com.devsahamerlin.agency.dto.DealerResponseDto;
import com.devsahamerlin.agency.entities.Dealer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealerMapper {
    DealerResponseDto dealerToDealerResponseDTO(Dealer dealer);
    Dealer dealerRequestDTOToDealer(DealerRequestDto dealerRequestDto);
}
