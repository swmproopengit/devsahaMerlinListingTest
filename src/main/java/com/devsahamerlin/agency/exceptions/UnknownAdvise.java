package com.devsahamerlin.agency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnknownAdvise extends RuntimeException{

    @ResponseBody
    @ExceptionHandler(UnKnowException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String UnknownHandler(UnKnowException ex) {
        return ex.getMessage();
    }
}
