package com.tnc.service.service;

import com.tnc.service.model.AnimalDomain;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
public interface AnimalService {

    AnimalDomain get(Long id);

    List<AnimalDomain> getAll();

    AnimalDomain add(@Valid AnimalDomain animalDomain);

    AnimalDomain update(@Valid AnimalDomain animalDomain);
}
