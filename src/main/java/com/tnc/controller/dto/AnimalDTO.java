package com.tnc.controller.dto;

import com.tnc.repository.shelter.Shelter;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;

import javax.validation.constraints.*;

public record AnimalDTO(
        @Null(message = "Id must be null", groups = OnCreate.class)
        @NotNull(message = "Id must not be null", groups = OnUpdate.class)
        @Positive
        Long id,
        @NotBlank
        @NotNull
        @Size(min = 3, max = 20, message = "Name must be between 3 and 20 chars")
        String name,
        @NotBlank
        @NotNull
        String breed,
        @NotBlank
        @NotNull
        String species,
        @NotBlank
        @NotNull
        String photo,
//        @NotNull
//        @NotBlank
        Shelter shelter
) {
}
