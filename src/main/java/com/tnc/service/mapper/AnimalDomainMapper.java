package com.tnc.service.mapper;

import com.tnc.repository.animalRepository.Animal;
import com.tnc.service.model.AnimalDomain;
import org.mapstruct.Mapping;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface AnimalDomainMapper {

    @Mapping(source = "id", target = "id")
    Animal toRepository(AnimalDomain animalDomain);

    @Mapping(source = "id", target = "id")
    List<Animal> toRepositoryList(List<AnimalDomain> animalDomainList);

    @Mapping(source = "id", target = "id")
    AnimalDomain toDomain(Animal animal);

    @Mapping(source = "id", target = "id")
    List<AnimalDomain> toDomainList(List<Animal> animal);

}
