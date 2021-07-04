package com.tnc.service.animalService;

import com.tnc.repository.iRepository.AnimalRepository;
import com.tnc.service.mapper.AnimalDomainMapper;
import com.tnc.service.model.AnimalDomain;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class AnimalAnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalDomainMapper animalDomainMapper;


    @Override
    public AnimalDomain get(Long id) {
        return animalDomainMapper.toDomain(animalRepository.getById(id));
    }

    @Override
    public List<AnimalDomain> getAll() {
        return animalDomainMapper.toDomainList(animalRepository.findAll());
    }

    @Validated(OnCreate.class)
    @Override
    public AnimalDomain add(AnimalDomain animalDomain) {
        var addAnimal = animalDomainMapper.toAnimal(animalDomain);
        return animalDomainMapper.toDomain(animalRepository.save(addAnimal));
    }

    @Validated(OnUpdate.class)
    @Override
    public AnimalDomain update(AnimalDomain animalDomain) {
        return animalDomainMapper.toDomain(animalRepository.save(animalDomainMapper.toAnimal(animalDomain)));
    }
}
