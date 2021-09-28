package com.tnc.service.impl;

import com.tnc.repository.interfaces.AnimalRepository;
import com.tnc.service.mapper.AnimalDomainMapper;
import com.tnc.service.domain.AnimalDomain;
import com.tnc.service.interfaces.AnimalService;
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
        return animalDomainMapper.toDomain(animalRepository.save(animalDomainMapper.toEntity(animalDomain)));
    }

    @Override
    public AnimalDomain update(AnimalDomain animalDomain) {
        return animalDomainMapper.toDomain(animalRepository.save(animalDomainMapper.toEntity(animalDomain)));
    }
}
