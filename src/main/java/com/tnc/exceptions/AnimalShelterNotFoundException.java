package com.tnc.exceptions;

public class AnimalShelterNotFoundException extends RuntimeException {
    public AnimalShelterNotFoundException(String message) {
        super(message);
    }
}
