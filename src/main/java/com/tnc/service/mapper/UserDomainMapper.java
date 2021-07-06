package com.tnc.service.mapper;

import com.tnc.repository.entity.User;
import com.tnc.service.model.UserDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDomainMapper {
    User toEntity(UserDomain userDomain);

    List<User> toEntityList(List<UserDomain> userDomainList);

    UserDomain toDomain(User User);

    List<UserDomain> toDomainList(List<User> userList);
}
