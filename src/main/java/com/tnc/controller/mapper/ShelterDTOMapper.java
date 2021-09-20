package com.tnc.controller.mapper;

import com.tnc.controller.dto.ShelterDTO;
import com.tnc.service.domain.ShelterDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelterDTOMapper {

    ShelterDomain toDomain(ShelterDTO shelterDTO);

    List<ShelterDomain> toDomainList(List<ShelterDTO> shelterDTOList);

    ShelterDTO toDTO(ShelterDomain shelterDomain);

    List<ShelterDTO> toDTOList(List<ShelterDomain> shelterDomainList);
}
