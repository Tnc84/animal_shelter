package com.tnc.repository.animal;


import com.tnc.repository.shelter.Shelter;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private String species;
    private String photo;

    @ManyToOne
    private Shelter shelter;
}
