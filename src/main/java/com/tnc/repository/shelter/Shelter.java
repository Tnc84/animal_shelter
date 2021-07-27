package com.tnc.repository.shelter;

import com.tnc.repository.animal.Animal;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    private List<Animal> animals = new ArrayList<>();
}


