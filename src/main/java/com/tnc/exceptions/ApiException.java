package com.tnc.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Getter
public class ApiException {

    private final String message;
//    private final Throwable throwable;///optional for see how the client see
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}
