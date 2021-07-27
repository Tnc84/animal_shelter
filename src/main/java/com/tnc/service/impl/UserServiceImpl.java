package com.tnc.service.impl;

import com.tnc.repository.user.UserRepository;
import com.tnc.service.interfaces.UserService;
import com.tnc.service.mapper.UserDomainMapper;
import com.tnc.service.model.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;

    @Override
    public UserDomain get(Long id) {
        return userDomainMapper.toDomain(userRepository.getById(id));
    }

    @Override
    public List<UserDomain> getAll() {
        return userDomainMapper.toDomainList(userRepository.findAll());
    }

    @Override
    public UserDomain add(UserDomain userDomain) {
        return userDomainMapper.toDomain(userRepository.save(userDomainMapper.toEntity(userDomain)));
    }

    @Override
    public UserDomain update(UserDomain userDomain) {
        return userDomainMapper.toDomain(userRepository.save(userDomainMapper.toEntity(userDomain)));
    }
}
