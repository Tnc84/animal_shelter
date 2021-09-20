package com.tnc.controller.mapper;

import com.tnc.controller.dto.UserDTO;
import com.tnc.service.domain.UserDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDomain toDomain(UserDTO userDTO);

    List<UserDomain> toDomainList(List<UserDTO> userDTOList);

    UserDTO toDTO(UserDomain userDomain);

    List<UserDTO> toDTOList(List<UserDomain> userDomainList);

}
