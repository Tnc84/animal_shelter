package com.tnc.service.service;

import com.tnc.service.model.UserDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDomain get(Long id);

    List<UserDomain> getAll();

    UserDomain add(UserDomain userDomain);

    UserDomain update(UserDomain userDomain);
}
