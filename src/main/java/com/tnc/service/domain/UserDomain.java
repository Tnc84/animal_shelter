package com.tnc.service.domain;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class UserDomain {
    private Long id;
    private String userId;
//    @NotBlank(message = "This field cannot be empty")
//    @NotNull(message = "Must not be null")
//    @NotEmpty(message = "This field must not be empty.")
//    @Length(message = "The name must be between 5 and 50 chars.", min = 5, max = 50)
//    @Pattern(message = "Must contain only letters.", regexp = "(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)")
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private Date joinDate;
    private String role;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;

}
