package com.devsahamerlin.agency.restcontroller;

import com.devsahamerlin.agency.dto.DealerResponseDto;
import com.devsahamerlin.agency.dto.ListingRequestDto;
import com.devsahamerlin.agency.dto.ListingResponseDto;
import com.devsahamerlin.agency.enums.ListingState;
import com.devsahamerlin.agency.services.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/listing")
public class ListingRestController{

    @Autowired
    private ListingService listingService;


    @Operation(
            summary = "Create new listing",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Request succeeded",
                            content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Dealer not found",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid or malformed request",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
                    @ApiResponse(responseCode = "507", description = "Dealer listing limit exceeded",
                            content = @Content)
            }
    )
    @PostMapping(path = "/save")
    public ResponseEntity<ListingResponseDto> createListing(
            @RequestBody ListingRequestDto listingRequestDto
    ) {
        return new ResponseEntity<>(listingService.createListing(listingRequestDto), HttpStatus.CREATED);

    }

    @Operation(
            summary = "Update listing",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Request succeeded",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Listing not found",
                            content = @Content),
                    @ApiResponse(responseCode = "406", description = "Dealer not found",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid or malformed request",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content)
            }
    )
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ListingResponseDto> updateListing(
            @PathVariable UUID id,
            @RequestBody ListingRequestDto listingRequestDto
    ) {

        return new ResponseEntity<>(listingService.updateListing(id, listingRequestDto), HttpStatus.ACCEPTED);

    }

    @Operation(
            summary = "Get listing by Dealer & State",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request succeeded",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Dealer not found",
                            content = @Content),
                    @ApiResponse(responseCode = "204", description = "Request succeeded, but not content",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid or malformed request",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content)
            }
    )
    @GetMapping(path = "/listing_by_state/{dealerId}/{state}")
    public ResponseEntity<List<ListingResponseDto>> getAllDealerListingByGivenState(
            @PathVariable UUID dealerId,
            @PathVariable ListingState state,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        if (listingService.getAllDealerListingByGivenState(dealerId, state, pageable).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(listingService.getAllDealerListingByGivenState(dealerId, state, pageable), HttpStatus.OK);
    }


    @Operation(
            summary = "Publish a listing",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Request succeeded",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Listing not found",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid or malformed request",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content)
            }
    )
    @PutMapping(path = "/publish/{id}")
    public ResponseEntity<Boolean> publishListing(@PathVariable UUID id) {
        return new ResponseEntity<>(listingService.publishListing(id), HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "UnPublish a listing",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Request succeeded",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Listing not found",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid or malformed request",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content)
            }
    )
    @PutMapping(path = "/un_publish/{id}")
    public ResponseEntity<Boolean> unPublishListing(@PathVariable UUID id) {
        return new ResponseEntity<>(listingService.unPublishListing(id), HttpStatus.ACCEPTED);
    }
}
