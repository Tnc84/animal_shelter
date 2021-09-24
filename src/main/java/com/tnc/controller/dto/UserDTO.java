package com.tnc.controller.dto;

import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.Date;

@Validated
public record UserDTO(

        Long id,
        String userId,
        String firstName,
        String lastName,
        String email,
        String username,
        String phone,
        String password,
        String profileImageUrl,
        Date lastLoginDate,
        Date lastLoginDateDisplay,
        Date joinDate,
        String role,
        String[] authorities,
        boolean isActive,
        boolean isNotLocked


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
