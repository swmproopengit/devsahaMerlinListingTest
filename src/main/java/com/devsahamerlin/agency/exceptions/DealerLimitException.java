package com.devsahamerlin.agency.exceptions;

import java.util.UUID;

public class DealerLimitException extends RuntimeException{
    public DealerLimitException(UUID dealerId, int actualListing, int limit){
        super("Dealer " + dealerId + " limit exceeded " + actualListing + " /" + limit + ".");
    }
}
