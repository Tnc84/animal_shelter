package com.tnc.service.mapper;

import com.tnc.repository.entities.User;
import com.tnc.service.domain.UserDomain;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserDomainMapper {

    User toEntity(UserDomain userDomain);

    List<User> toEntityList(List<UserDomain> userDomainList);

    UserDomain toDomain(User User);

    List<UserDomain> toDomainList(List<User> userList);

}
