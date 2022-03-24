package com.devsahamerlin.agency.entities;

import com.devsahamerlin.agency.enums.ListingState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Listing implements Serializable {
    @Id
    private UUID id;
    @NotNull(message = "dealerId is mandatory")
    private UUID dealerId;
    @NotNull(message = "vehicle is mandatory")
    private String vehicle;
    @NotNull
    private Number price;
    private Date createdAt;
    private ListingState state;
}
