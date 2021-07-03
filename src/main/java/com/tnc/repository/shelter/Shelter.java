package com.tnc.repository.shelter;

import com.tnc.repository.animal.Animal;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    private List<Animal>animalList = new ArrayList<>();
}


