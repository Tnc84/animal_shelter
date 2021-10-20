package com.tnc.repository.entities;

import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "Must be positive number")
    @Null(message = "Id must be null", groups = OnCreate.class)
    @NotNull(message = "Id must not be null", groups = OnUpdate.class)
    private Long id;
    private String userId;
    @NotBlank(message = "This field cannot be empty")
    @NotNull(message = "Must not be null")
    @NotEmpty(message = "This field must not be empty.")
    @Length(message = "The name must be between 5 and 50 chars.", min = 5, max = 50)
    @Pattern(message = "Must contain only letters.", regexp = "(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)")
    private String firstName;
    @NotBlank(message = "This field cannot be empty")
    @NotNull(message = "Must not be null")
    @NotEmpty(message = "This field must not be empty.")
    @Length(message = "The name must be between 5 and 50 chars.", min = 5, max = 50)
    @Pattern(message = "Must contain only letters.", regexp = "(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)")
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Pattern(message = "Must contains only digits.", regexp = "0-9")
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

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id",
//                    referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id",
//                    referencedColumnName = "id"))
//    private Set<Role> roles = new HashSet<>();
}
