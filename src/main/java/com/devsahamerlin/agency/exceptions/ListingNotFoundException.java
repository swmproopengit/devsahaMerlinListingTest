package com.devsahamerlin.agency.exceptions;

import java.util.UUID;

public class ListingNotFoundException extends RuntimeException{
    public ListingNotFoundException(UUID message){
        super("Could not find listing " + message + " in the system.");
    }
}
