package com.tnc.controller.dto;

import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
public record UserDTO(

        @Null(message = "Id must be null", groups = OnCreate.class)
        @NotNull(message = "Id must not be null", groups = OnUpdate.class)
        @Positive
        Long id,
        @NotNull
        @NotBlank
        @Size(min = 3, max = 50, message = "This field must have between 3 and 50 chars")
        String firstName,
        @NotNull
        @NotBlank
        @Size(min = 3, max = 50, message = "This field must have between 3 and 50 chars")
        String lastName,
        @NotNull
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        @Size(min = 10, max = 10)
        String phone
) {
}
