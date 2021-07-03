package com.tnc.service.animalService;

import com.tnc.repository.animalRepository.AnimalRepository;
import com.tnc.service.mapper.AnimalDomainMapper;
import com.tnc.service.model.AnimalDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    @Override
    public AnimalDomain add(AnimalDomain animalDomain) {
        var addAnimal = animalDomainMapper.toRepository(animalDomain);
        animalRepository.save(addAnimal);
        return animalDomain;
    }

    @Override
    public AnimalDomain update(AnimalDomain animalDomain) {
        return animalDomainMapper.toDomain(animalRepository.save(animalDomainMapper.toRepository(animalDomain)));
    }
}
