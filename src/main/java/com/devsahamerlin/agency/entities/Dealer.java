package com.devsahamerlin.agency.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dealer implements Serializable {
    @Id
    private UUID id;
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Name is mandatory")
    private int listingLimit;
}
