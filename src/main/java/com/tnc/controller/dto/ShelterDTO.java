package com.tnc.controller.dto;

import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Validated
@AllArgsConstructor
@Getter
public class ShelterDTO {
    @NotNull(message = "Id must not be null", groups = OnUpdate.class)
    @Null(message = "Id must be null", groups = OnCreate.class)
    @Positive
    Long id;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 chars")
    String name;
    @NotNull
    @NotBlank
    String city;
    @NotNull
//        @NotBlank
    List<AnimalDTO> animals = new ArrayList<>();
}
