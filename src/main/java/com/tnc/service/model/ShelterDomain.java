package com.tnc.service.model;

import java.util.List;

public record ShelterDomain(
        Long id,
        String name,
        String address,
        List<AnimalDomain> animalDomainList
) {
}
