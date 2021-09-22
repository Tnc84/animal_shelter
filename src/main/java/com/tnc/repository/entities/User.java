package com.tnc.repository.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Data
@Accessors(chain = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "Must be positive number")
    private Long id;
    private String userId;
    //        @NotBlank(message = "This field cannot be empty")
//        @NotNull(message = "Must not be null")
//        @Length(message = "The name must be between 5 and 100 chars.", min = 5, max = 100)
//        @NotEmpty(message = "This field must not be empty.")
//        @Pattern(message = "Must contain only letters.", regexp = "A-Z, a-z")
    private String firstName;
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
    private boolean isNotActive;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id",
//                    referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id",
//                    referencedColumnName = "id"))
//    private Set<Role> roles = new HashSet<>();
///for JWT

}
