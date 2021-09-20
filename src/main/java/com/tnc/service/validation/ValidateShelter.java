package com.tnc.service.validation;

import com.tnc.service.exception.ShelterAddressException;
import com.tnc.service.domain.ShelterDomain;
import java.util.Locale;

public class ValidateShelter {

    public static void validateShelter(ShelterDomain shelterDomain) throws ShelterAddressException {
        if (!shelterDomain.getCity().toLowerCase(Locale.ROOT).contains("iasi")){
            throw new ShelterAddressException("The shelter is not from Iasi");
        }
    }
}
