package com.devsahamerlin.agency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ListingNotFoundAdvise extends RuntimeException{

    @ResponseBody
    @ExceptionHandler(ListingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String listingNotFoundHandler(ListingNotFoundException ex) {
        return ex.getMessage();
    }
}
