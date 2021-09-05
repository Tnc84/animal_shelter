package com.tnc.service.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShelterDomain {
    private Long id;
    private String name;
    private String city;
    private List<AnimalDomain> animals = new ArrayList<>();

}
