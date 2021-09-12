package com.tnc.service.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Violation implements Serializable {

    private String field;
    private String message;
    private String receivedValue;

    public Violation(String field, String message, String receivedValue) {
        this.field = field;
        this.message = message;
        this.receivedValue = receivedValue;
    }

    public Violation(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
