package com.tnc.service.model;

import com.tnc.repository.shelter.Shelter;

public record AnimalDomain(
        Long id,
        String name,
        String breed,
        String species,
        String photo,
        Shelter shelter
) {
}
