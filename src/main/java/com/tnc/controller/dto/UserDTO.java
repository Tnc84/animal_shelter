package com.tnc.controller.dto;

import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.Date;

@Validated
public record UserDTO(

        @Null(message = "Id must be null", groups = OnCreate.class)
        @NotNull(message = "Id must not be null", groups = OnUpdate.class)
        @Positive
        Long id,
//        String userId,
        String firstName,
        String lastName,
        String email,
//        String phone,
//        String password,
//        String profileImageUrl,
        String username
//        Date lastLoginDate,
//        Date lastLoginDateDisplay,
//        Date joinDate,
//        String role,
//        String[] authorities,
//        boolean isActive,
//        boolean isNotLocked


//        @Positive
//        Long id,
//        @NotNull
//        @NotBlank
//        @Size(min = 3, max = 50, message = "This field must have between 3 and 50 chars")
//        String firstName,
//        @NotNull
//        @NotBlank
//        @Size(min = 3, max = 50, message = "This field must have between 3 and 50 chars")
//        String lastName,
//        @NotNull
//        @NotBlank
//        @Email
//        String email,
//        @NotNull
//        @NotBlank
//        @Size(min = 10, max = 10)
//        String phone
) {
}
