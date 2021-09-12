package com.tnc.service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class ShelterDomain {
    private Long id;
    private String name;
    private String city;
    private List<AnimalDomain> animals = new ArrayList<>();

}
