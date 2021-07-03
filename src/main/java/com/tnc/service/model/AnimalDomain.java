package com.tnc.service.model;

public record AnimalDomain(
        Long id,
        String name,
        String breed,
        String species,
        String photo
) {
}
