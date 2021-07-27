package com.tnc.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ShelterDomain{
       private Long id;
       private String name;
       private String city;
       private List<AnimalDomain> animals = new ArrayList<>();

}
