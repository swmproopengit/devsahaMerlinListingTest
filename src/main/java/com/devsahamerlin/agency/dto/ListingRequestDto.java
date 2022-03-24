package com.devsahamerlin.agency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingRequestDto {
    @ApiModelProperty(value="Id of a dealer who own this vehicle.", required=true)
    @NotNull
    @JsonProperty(value= "dealerId")
    private UUID dealerId;

    @ApiModelProperty(value="The name of a vehicle: mark and model.", required=true)
    @NotNull
    @JsonProperty(value= "vehicle")
    private String vehicle;

    @ApiModelProperty(value="A price of a vehicle.", required=true)
    @NotNull
    @JsonProperty(value= "price")
    private Number price;
}
