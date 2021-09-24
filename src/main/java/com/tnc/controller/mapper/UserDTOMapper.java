package com.tnc.controller.mapper;

import com.tnc.controller.dto.UserDTO;
import com.tnc.controller.dto.UserDTOForRegister;
import com.tnc.repository.entities.User;
import com.tnc.service.domain.UserDomain;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDomain toDomain(UserDTO userDTO);

    List<UserDomain> toDomainList(List<UserDTO> userDTOList);

    UserDTO toDTO(UserDomain userDomain);

    List<UserDTO> toDTOList(List<UserDomain> userDomainList);

    UserDomain toDomainRegistration(UserDTOForRegister userDTO);

    UserDTOForRegister toDTORegistration(UserDomain userDomain);

}
