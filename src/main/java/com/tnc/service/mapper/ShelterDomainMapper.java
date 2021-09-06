package com.tnc.service.mapper;

import com.tnc.repository.entities.Shelter;
import com.tnc.service.model.ShelterDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelterDomainMapper {
    Shelter toEntity(ShelterDomain shelterDomain);

    List<Shelter> toEntity(List<ShelterDomain> shelterDomainList);

    ShelterDomain toDomain(Shelter shelter);

    List<ShelterDomain> toDomainList(List<Shelter> shelterList);
}
