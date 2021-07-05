package com.tnc.service.serviceImpl;

import com.tnc.repository.iRepository.AnimalRepository;
import com.tnc.service.mapper.AnimalDomainMapper;
import com.tnc.service.model.AnimalDomain;
import com.tnc.service.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

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
        var addAnimal = animalDomainMapper.toAnimal(animalDomain);
        return animalDomainMapper.toDomain(animalRepository.save(addAnimal));
    }

    @Override
    public AnimalDomain update(AnimalDomain animalDomain) {
        return animalDomainMapper.toDomain(animalRepository.save(animalDomainMapper.toAnimal(animalDomain)));
    }
}
