package com.devsahamerlin.agency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DealerLimitAdvise extends RuntimeException{

    @ResponseBody
    @ExceptionHandler(DealerLimitException.class)
    @ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
    String dealerLimitHandler(DealerLimitException ex) {
        return ex.getMessage();
    }
}
