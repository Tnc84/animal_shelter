package com.tnc.service.mapper;

import com.tnc.repository.entity.Shelter;
import com.tnc.service.model.ShelterDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelterDomainMapper {
    Shelter toShelter(ShelterDomain shelterDomain);

    List<Shelter> toShelterList(List<ShelterDomain> shelterDomainList);

    ShelterDomain toDomain(Shelter shelter);

    List<ShelterDomain> toDomainList(List<Shelter> shelterList);
}
