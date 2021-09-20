package com.tnc.service.impl;

import com.tnc.repository.repositories.ShelterRepository;
import com.tnc.service.exception.ShelterAddressException;
import com.tnc.service.interfaces.ShelterService;
import com.tnc.service.mapper.ShelterDomainMapper;
import com.tnc.service.domain.ShelterDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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
        return shelterDomainMapper.toDomainList(shelterRepository.findAll());
    }

    @Override
    public ShelterDomain add(ShelterDomain shelterDomain) {
//        ValidateShelter.validateShelter(shelterDomain);
//        validateShelter(shelterDomain);
        var addShelter = shelterDomainMapper.toEntity(shelterDomain);
        return shelterDomainMapper.toDomain(shelterRepository.save(addShelter));
    }

    @Override
    public ShelterDomain update(ShelterDomain shelterDomain) {
        return shelterDomainMapper.toDomain(shelterDomainMapper.toEntity(shelterDomain));
    }

    public void validateShelter(ShelterDomain shelterDomain) throws Exception{
        if (!shelterDomain.getCity().toLowerCase(Locale.ROOT).contains("iasi")) {
            throw new ShelterAddressException("The shelter is not from Iasi");
        }
    }
}
