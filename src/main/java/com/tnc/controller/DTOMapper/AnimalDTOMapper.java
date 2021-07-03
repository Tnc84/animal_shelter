package com.tnc.controller.DTOMapper;

import com.tnc.controller.dto.AnimalDTO;
import com.tnc.service.model.AnimalDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalDTOMapper {

    @Mapping(source = "id", target = "id")
    AnimalDomain toDomain(AnimalDTO animalDTO);

    @Mapping(source = "id", target = "id")
    List<AnimalDomain> toDomainList(List<AnimalDTO> animalDTO);

    @Mapping(source = "id", target = "id")
    AnimalDTO toDTO(AnimalDomain animalDomain);

    @Mapping(source = "id", target = "id")
    List<AnimalDTO> toDTOList(List<AnimalDomain> animalDomainList);

}
