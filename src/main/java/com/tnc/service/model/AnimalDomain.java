package com.tnc.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDomain {
    Long id;
    String name;
    String breed;
    String species;
    String photo;
}
