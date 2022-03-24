package com.devsahamerlin.agency.restcontroller;

import com.devsahamerlin.agency.dto.DealerRequestDto;
import com.devsahamerlin.agency.dto.DealerResponseDto;
import com.devsahamerlin.agency.services.DealerService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/dealers")
@Api(tags = "Dealer RestController")
public class DealerRestController{
    @Autowired
    private DealerService dealerService;


    @Operation(
            summary = "Save new dealer in system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Dealer successfully created", content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerResponseDto.class)) }),
            }
    )
    @PostMapping(path = "/save")
    public ResponseEntity<DealerResponseDto> addDealer(@Valid @RequestBody DealerRequestDto dealerRequestDto) {
        return new ResponseEntity<>(dealerService.addDealer(dealerRequestDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a dealer by its id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dealer found", content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Dealer not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid dealer id supplied or malformed request", content = @Content)
            }
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<DealerResponseDto> getDealerById(
            @Parameter(description = "id of dealer to be searched")
            @PathVariable UUID id) {
        return new ResponseEntity<>(dealerService.getDealerById(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Update dealer limit",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Limit successfully updated", content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Dealer not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid dealer id supplied or malformed request", content = @Content)
            }
    )
    @PutMapping(path = "/limits/{id}")
    public ResponseEntity<String> updateDealerLimit(
            @Parameter(description = "number of published listings for a dealer")
            @RequestParam int listingLimit,
            @Parameter(description = "id of dealer to be searched")
            @PathVariable UUID id
            ) {

        return new ResponseEntity<>(dealerService.updateDealerLimit(listingLimit,id), HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Get dealer limit",
            responses = {
                    @ApiResponse(responseCode = "20O", description = "Request succeeded", content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerResponseDto.class)) }),
                    @ApiResponse(responseCode = "404", description = "Dealer not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid dealer id supplied or malformed request", content = @Content)
            }
    )
    @GetMapping(path = "/limits/{id}")
    public ResponseEntity<Integer> getDealerLimit(
            @Parameter(description = "id of dealer to be searched")
            @PathVariable UUID id
    ) {
        return new ResponseEntity<>(dealerService.getDealerLimit(id), HttpStatus.OK);
    }

}
