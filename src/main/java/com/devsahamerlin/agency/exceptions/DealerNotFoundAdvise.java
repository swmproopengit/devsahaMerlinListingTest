package com.devsahamerlin.agency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DealerNotFoundAdvise extends RuntimeException{

    @ResponseBody
    @ExceptionHandler(DealerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String dealerNotFoundHandler(DealerNotFoundException ex) {
        return ex.getMessage();
    }
}
