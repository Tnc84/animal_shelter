package com.tnc.service.model;

import com.tnc.repository.animal.Animal;
import com.tnc.service.validation.OnCreate;

import javax.validation.constraints.*;
import java.util.List;

public record ShelterDomain(
        Long id,
        String name,
        String address,
        List<Animal> animalList
) {
}
