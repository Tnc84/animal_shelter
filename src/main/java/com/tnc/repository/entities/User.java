package com.tnc.repository.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
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
    private Long id;
    private String userId;
    //        @NotBlank(message = "This field cannot be empty")
//        @NotNull(message = "Must not be null")
//        @Length(message = "The name must be between 5 and 100 chars.", min = 5, max = 100)
//        @NotEmpty(message = "This field must not be empty.")
//        @Pattern(message = "Must contain only letters.", regexp = "A-Z, a-z")
    private String firstName;
    private String lastName;
//    @Column(nullable = false, unique = true)
    private String username;
//    @Column(nullable = false, unique = true)
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
///for JWT

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 0;
//    }
}
