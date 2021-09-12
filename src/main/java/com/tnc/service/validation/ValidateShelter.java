package com.tnc.service.validation;

import com.tnc.service.exceptions.ShelterAddressException;
import com.tnc.service.exceptions.Violation;
import com.tnc.service.model.ShelterDomain;
import java.util.Locale;

public class ValidateShelter {

    public static void validateShelter(ShelterDomain shelterDomain) {
        if (!shelterDomain.getCity().toLowerCase(Locale.ROOT).contains("iasi")){
            throw new ShelterAddressException(new Violation("message", "The shelter is not from Iasi", shelterDomain.getCity()));
        }
    }
}
