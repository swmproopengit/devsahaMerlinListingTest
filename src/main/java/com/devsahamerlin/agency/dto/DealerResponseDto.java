package com.devsahamerlin.agency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DealerResponseDto {
    private UUID id;
    private String name;
    private int listingLimit;
}
