package com.tnc.service.impl;

import com.tnc.repository.entities.User;
import com.tnc.repository.repositories.UserRepository;
import com.tnc.service.domain.Role;
import com.tnc.service.domain.UserDomain;
import com.tnc.service.exception.EmailExistException;
import com.tnc.service.exception.UserNotFoundException;
import com.tnc.service.exception.UsernameExistException;
import com.tnc.service.interfaces.UserService;
import com.tnc.service.mapper.UserDomainMapper;
import com.tnc.service.security.PasswordEncoder;
import com.tnc.service.security.UserPrincipal;
import com.tnc.service.validation.UserValidation;
import com.tnc.service.validation.ValidateUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@Transactional
@Qualifier("userDetailsService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass()); //getClass = this class
    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDomain register(UserDomain userDomain) throws UserNotFoundException, EmailExistException, UsernameExistException {

//        validateNewUsernameAndEmail(StringUtils.EMPTY, userDomain.getUsername(), userDomain.getEmail());
        ValidateUser.validateNewUserName(userDomain);
        UserDomain user = new UserDomain();
        user.setUserId(generateUserId());
        String password = generatePassword();
        String encodedPassword = encodePassword(password);
        user.setFirstName(userDomain.getFirstName());
        user.setLastName(userDomain.getLastName());
        user.setUsername(userDomain.getUsername());
        user.setEmail(userDomain.getEmail());
        user.setJoinDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileImageUrl());
        LOGGER.info("New user password " + password);
        return userDomainMapper.toDomain(userRepository.save(userDomainMapper.toEntity(user)));
    }

//    private UserDomain validateNewUserName(UserDomain userDomain) throws UsernameExistException {
//        if (!userDomain.getUsername().isEmpty() && !userDomain.getFirstName().isEmpty() && !userDomain.getLastName().isEmpty()){
//            throw new UsernameExistException("Username already exist");
//        }
//        return userDomain;
//    }

    private String getTemporaryProfileImageUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/image/profile/temp").toUriString();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

//    private UserDomain validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UsernameExistException, EmailExistException, UserNotFoundException {
//        if (StringUtils.isNotBlank(currentUsername)) {
//            var currentUser = findByUsername(currentUsername);
//            if (currentUser == null) {
//                throw new UserNotFoundException("No user found by username " + currentUsername);
//            }
//            var userByNewUsername = findByUsername(newUsername);
//            if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
//                throw new UsernameExistException("Username already exist");
//            }
//            var userByEmail = findByEmail(newEmail);
//            if (userByEmail != null && !currentUser.getId().equals(userByEmail.getId())) {
//                throw new EmailExistException("Email already exist");
//            }
//            return currentUser;
//        } else {
//            var userByUsername = findByUsername(newUsername);
//            if (userByUsername != null) {
//                throw new UsernameExistException("Username already exist");
//            }
//            var userByEmail = findByEmail(newEmail);
//            if (userByEmail != null) {
//                throw new EmailExistException("Email already exist");
//            }
//            return null;
//        }
//    }

    @Override
    public UserDomain findByUsername(String username) {
        return null;
    }

    @Override
    public UserDomain findByEmail(String email) {
        return null;
    }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error("User not found by username " + username);
            throw new UsernameNotFoundException("User not found by username " + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returnig found user by username " + username);
            return userPrincipal;
        }
    }
}
