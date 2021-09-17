package com.tnc.service.model;

import lombok.Data;

@Data
public class AnimalDomain {
    Long id;
    String name;
    String breed;
    String species;
    String photo;
}
