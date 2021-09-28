package com.tnc.controller.mapper;

import com.tnc.controller.dto.UserDTO;
import com.tnc.service.domain.UserDomain;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDomain toDomain(UserDTO userDTO);

    List<UserDomain> toDomainList(List<UserDTO> userDTOList);

    UserDTO toDTO(UserDomain userDomain);

    List<UserDTO> toDTOList(List<UserDomain> userDomainList);

    Object toDomainWithStringParam(String firstName, String lastName, String username, String email, String role, boolean parseBoolean, boolean parseBoolean1, MultipartFile profileImage);
    Object toDomainForUpdate(String currentUsername, String firstName, String lastName, String username, String email, String role, boolean parseBoolean, boolean parseBoolean1, MultipartFile profileImage);
}
