package com.tnc.service.serviceImpl;

import com.tnc.repository.iRepository.ShelterRepository;
import com.tnc.service.mapper.ShelterDomainMapper;
import com.tnc.service.model.ShelterDomain;
import com.tnc.service.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDomainMapper shelterDomainMapper;

    @Override
    public ShelterDomain get(Long id) {
        return shelterDomainMapper.toDomain(shelterRepository.getById(id));
    }

    @Override
    public List<ShelterDomain> getAll() {
        var getAll = shelterDomainMapper.toDomainList(shelterRepository.findAll());
        return getAll;
    }

    @Override
    public ShelterDomain add(ShelterDomain shelterDomain) {
        var addShelter = shelterDomainMapper.toEntity(shelterDomain);
        return shelterDomainMapper.toDomain(shelterRepository.save(addShelter));
    }

    @Override
    public ShelterDomain update(ShelterDomain shelterDomain) {
        return shelterDomainMapper.toDomain(shelterDomainMapper.toEntity(shelterDomain));
    }
}
