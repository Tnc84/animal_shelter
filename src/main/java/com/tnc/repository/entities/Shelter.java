package com.tnc.repository.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Shelter {

    @Positive(message = "Must be positive number")
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "This field cannot be empty")
    @NotEmpty(message = "This field must not be empty.")
    @Pattern(message = "Must contain only letters.", regexp = "A-Z, a-z")
    @NotNull(message = "Must not be null")
    @Length(message = "The name must be between 5 and 100 chars.", min = 5, max = 100)
    private String name;
    @NotBlank(message = "This field cannot be empty")
    @NotEmpty(message = "This field must not be empty.")
    @Pattern(message = "Must contain only letters.", regexp = "A-Z, a-z")
    @NotNull(message = "Must not be null")
    @Length(message = "The name must be between 5 and 100 chars.", min = 5, max = 100)
    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    private List<Animal> animals = new ArrayList<>();

    private String environment;
}


