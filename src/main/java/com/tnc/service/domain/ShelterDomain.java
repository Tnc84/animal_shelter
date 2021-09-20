package com.tnc.service.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
//@Accessors(chain = true)
public class ShelterDomain {
    private Long id;
    private String name;
    private String city;
    private List<AnimalDomain> animals = new ArrayList<>();

}
