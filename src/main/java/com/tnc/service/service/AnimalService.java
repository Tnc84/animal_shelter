package com.tnc.service.service;

import com.tnc.service.model.AnimalDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {

    AnimalDomain get(Long id);

    List<AnimalDomain> getAll();

    AnimalDomain add(AnimalDomain animalDomain);

    AnimalDomain update(AnimalDomain animalDomain);
}
