package com.tnc.service.validation;

import com.tnc.exceptions.ApiException;
import com.tnc.service.model.ShelterDomain;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Locale;

public class ValidateShelter {

    public static void validateShelter(ShelterDomain shelterDomain) throws ApiException {
        if (!shelterDomain.getCity().toLowerCase(Locale.ROOT).contains("iasi")){
            throw new ApiException("The shelter is not from Iasi", HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        }
    }
}
