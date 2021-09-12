package com.tnc.controller.dto;

import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
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
        String photo
) {
}
