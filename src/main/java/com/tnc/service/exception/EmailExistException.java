package com.tnc.service.exception;

public class EmailExistException extends Exception{
    public EmailExistException(String message) {
        super(message);
    }
}
