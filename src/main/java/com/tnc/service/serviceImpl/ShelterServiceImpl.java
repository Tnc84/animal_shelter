package com.tnc.service.serviceImpl;

import com.tnc.repository.iRepository.ShelterRepository;
import com.tnc.service.mapper.ShelterDomainMapper;
import com.tnc.service.model.ShelterDomain;
import com.tnc.service.service.ShelterService;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDomainMapper shelterDomainMapper;

    @Override
    public ShelterDomain get(Long id) {
        return shelterDomainMapper.toDomain(shelterRepository.getById(id));
    }

    @Override
    public List<ShelterDomain> getAll() {
        return shelterDomainMapper.toDomainList(shelterRepository.findAll());
    }

    @Override
    @Validated(OnCreate.class)
    public ShelterDomain add(ShelterDomain shelterDomain) {
        var addShelter = shelterDomainMapper.toShelter(shelterDomain);
        return shelterDomainMapper.toDomain(shelterRepository.save(addShelter));
    }

    @Override
    @Validated(OnUpdate.class)
    public ShelterDomain update(ShelterDomain shelterDomain) {
        return shelterDomainMapper.toDomain(shelterDomainMapper.toShelter(shelterDomain));
    }
}
