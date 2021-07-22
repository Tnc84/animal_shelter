package com.tnc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ApiExceptionHandler {

    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        var badRequest = HttpStatus.BAD_REQUEST;
        var apiException = new ApiException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
        ///min 8:28
    }
}
