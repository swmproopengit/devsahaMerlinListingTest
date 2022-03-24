package com.devsahamerlin.agency.exceptions;


public class UnKnowException extends RuntimeException{
    public UnKnowException(){
        super("Internal server error.");
    }
}
