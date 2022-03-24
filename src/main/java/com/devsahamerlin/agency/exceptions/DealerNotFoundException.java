package com.devsahamerlin.agency.exceptions;

import java.util.UUID;

public class DealerNotFoundException extends RuntimeException{
    public DealerNotFoundException(UUID message){
        super("Could not find Dealer " + message + " in the system.");
    }
}
