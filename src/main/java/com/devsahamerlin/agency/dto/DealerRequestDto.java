package com.devsahamerlin.agency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DealerRequestDto {
    @ApiModelProperty(value="Name of dealer or business name that owns this dealer.", required=true)
    @NotNull
    @JsonProperty(value= "name")
    private String name;

    @ApiModelProperty(value="Total num of listing dealer can publish.", required=true)
    @NotNull
    @JsonProperty(value= "listingLimit")
    private int listingLimit;
}
