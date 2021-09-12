package com.tnc.service.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionsHandlerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(HttpStatus.EXPECTATION_FAILED, "Payload not ok.", ex.getMessage()), status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiError onConstraintValidationException(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "missing arguments");

        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            apiError.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage(), violation.getInvalidValue().toString()));
        }
        return apiError;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, ex.getMessage()), status);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    ApiError onValidationException(ValidationException ex) {
        return ex.getApiError();
    }


    @ExceptionHandler(ShelterLocationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiError onShelterLocationException(ShelterLocationException ex) {
        return new ApiError(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
    }

    @ExceptionHandler(ShelterAddressException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    ApiError onShelterAddressFail(ShelterAddressException ex) {
        ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED, ex.getMessage());

        apiError.getViolations().add(ex.getViolation());

        return apiError;
    }

    @ExceptionHandler(AnimalShelterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ApiError onShelterNotFound(AnimalShelterNotFoundException ex) {
        return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
