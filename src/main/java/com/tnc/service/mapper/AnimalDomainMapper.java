package com.tnc.service.mapper;

import com.tnc.repository.entity.Animal;
import com.tnc.service.model.AnimalDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalDomainMapper {

    @Mapping(source = "id", target = "id")
    Animal toAnimal(AnimalDomain animalDomain);

    @Mapping(source = "id", target = "id")
    List<Animal> toAnimalList(List<AnimalDomain> animalDomainList);

    @Mapping(source = "id", target = "id")
    AnimalDomain toDomain(Animal animal);

    @Mapping(source = "id", target = "id")
    List<AnimalDomain> toDomainList(List<Animal> animal);
}
