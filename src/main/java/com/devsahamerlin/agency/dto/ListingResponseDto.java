package com.devsahamerlin.agency.dto;

import com.devsahamerlin.agency.enums.ListingState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListingResponseDto {
    private UUID id;
    private UUID dealerId;
    private String vehicle;
    private Number price;
    private Date createdAt;
    private ListingState state;
}
