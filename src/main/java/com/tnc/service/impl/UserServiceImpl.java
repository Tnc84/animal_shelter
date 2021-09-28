package com.tnc.service.impl;

import com.tnc.repository.entities.User;
import com.tnc.repository.repositories.UserRepository;
import com.tnc.service.domain.Role;
import com.tnc.service.domain.UserDomain;
import com.tnc.service.exception.EmailExistException;
import com.tnc.service.exception.EmailNotFoundException;
import com.tnc.service.exception.UserNotFoundException;
import com.tnc.service.exception.UsernameExistException;
import com.tnc.service.interfaces.UserService;
import com.tnc.service.mapper.UserDomainMapper;
import com.tnc.service.security.PasswordEncoder;
import com.tnc.service.security.UserPrincipal;
import com.tnc.service.security.preventBroteForceAttack.LoginAttemptService;
import com.tnc.service.security.util.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static com.tnc.service.constant.FileConstant.*;
import static com.tnc.service.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static com.tnc.service.constant.UserImplConstant.*;
import static com.tnc.service.domain.Role.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
@Qualifier("userDetailsService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    //remove final from logger
    private final Logger LOGGER = LoggerFactory.getLogger(getClass()); //getClass = this class

    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;
    private final PasswordEncoder passwordEncoder;
    //    @Autowired // field or setter injection for avoid circular dependencies
//    private AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final LoginAttemptService loginAttemptService;
    private final EmailService emailService;

    public UserPrincipal returnForLoginMethod(UserDomain userDomain) {
        var loginUser = userDomainMapper.toDomain(userRepository.findUserByUsername(userDomain.getUsername()));
        return new UserPrincipal(userDomainMapper.toEntity(loginUser));
    }

    public HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

//    public void authenticate(String username, String password) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//    }

    @Override
    public UserDomain register(UserDomain userDomain) throws MessagingException {

        userDomain.setUserId(generateUserId());
        String password = generatePassword();
        String encodedPassword = encodePassword(password);
        userDomain.setJoinDate(new Date());
        userDomain.setPassword(encodedPassword);
        userDomain.setActive(true);
        userDomain.setNotLocked(true);
        userDomain.setRole(ROLE_USER.name());
        userDomain.setAuthorities(ROLE_USER.getAuthorities());
        userDomain.setProfileImageUrl(getTemporaryProfileImageUrl(userDomain.getUsername()));
        userRepository.save(userDomainMapper.toEntity(userDomain));
        LOGGER.info("New userDomain password " + password);//this line must be removed
        emailService.sendNewPasswordEmail(userDomain.getFirstName(), password, userDomain.getEmail());
        return userDomain;
    }

    @Override
    public UserDomain addNewUser(String firstName, String laseName, String username, String email, String role, boolean isActive, boolean isNotActive, MultipartFile profileImage) throws IOException {
        var userDomain = new UserDomain();
        String password = generatePassword();
        userDomain.setUserId(generateUserId());
        userDomain.setFirstName(firstName);
        userDomain.setLastName(laseName);
        userDomain.setJoinDate(new Date());
        userDomain.setUsername(username);
        userDomain.setEmail(email);
        userDomain.setPassword(encodePassword(password));
        userDomain.setActive(isActive);
        userDomain.setNotLocked(isNotActive);
        userDomain.setRole(getRoleEnumName(role).name());
        userDomain.setAuthorities(getRoleEnumName(role).getAuthorities());
        userDomain.setProfileImageUrl(getTemporaryProfileImageUrl(username));
        userRepository.save(userDomainMapper.toEntity(userDomain));
        saveProfileImage(userDomain, profileImage);
        return userDomain;
    }

    @Override
    public UserDomain updateUser(String currentUsername, String newFirstName, String newLaseName, String newUsername, String newEmail, String role, boolean isActive, boolean isNotActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException {
        var userDomain = validateNewUsernameAndEmail(currentUsername, newUsername, newEmail);
        userDomain.setFirstName(newFirstName);
        userDomain.setLastName(newLaseName);
        userDomain.setJoinDate(new Date());
        userDomain.setUsername(newUsername);
        userDomain.setEmail(newEmail);
        userDomain.setActive(isActive);
        userDomain.setNotLocked(isNotActive);
        userDomain.setRole(getRoleEnumName(role).name());
        userDomain.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepository.save(userDomainMapper.toEntity(userDomain));
        saveProfileImage(userDomain, profileImage);
        return userDomain;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException {
        var userDomain = userDomainMapper.toDomain(userRepository.findUserByEmail(email));
        if (userDomain == null) {
            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
        }
        String password = generatePassword();
        userDomain.setPassword(encodePassword(password));
        userRepository.save(userDomainMapper.toEntity(userDomain));
        emailService.sendNewPasswordEmail(userDomain.getFirstName(), password, userDomain.getEmail());
    }

    @Override
    public UserDomain updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException {
        var userDomain = validateNewUsernameAndEmail(username, null, null);
        saveProfileImage(userDomain, profileImage);
        return userDomain;
    }

    @Override
    public UserDomain findByUsername(String username) {
        return userDomainMapper.toDomain(userRepository.findUserByUsername(username));
    }

    @Override
    public UserDomain findByEmail(String email) {
        return userDomainMapper.toDomain(userRepository.findUserByEmail(email));
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
        var user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(user);// check if the account is not locked before setting the user
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            return userPrincipal;
        }
    }

    private void validateLoginAttempt(User user) {
        if (user.isNotActive()) {
            if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotActive(false);
            } else {
                user.setNotActive(true);
            }
        } else {
            loginAttemptService.evictUserForLoginAttemptCache(user.getUsername());
        }
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

    private String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }

    private void saveProfileImage(UserDomain userDomain, MultipartFile profileImage) throws IOException {
        if (profileImage != null) {
            Path userFolder = Paths.get(USER_FOLDER + userDomain.getUsername()).toAbsolutePath().normalize();
            if (Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + userDomain.getUsername() + DOT + JPG_EXTENSION)); //this line can be replaced by the REPLACE_EXISTING
            Files.copy(profileImage.getInputStream(), userFolder.resolve(userDomain.getUsername() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            userDomain.setProfileImageUrl(setProfileImageUrl(userDomain.getUsername()));
            userRepository.save(userDomainMapper.toEntity(userDomain));
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }

    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username + FORWARD_SLASH + username + DOT + JPG_EXTENSION).toUriString();
    }

    private Role getRoleEnumName(String role) {
        return valueOf(role.toUpperCase());
    }

    private UserDomain validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UsernameExistException, EmailExistException, UserNotFoundException {
        var userByNewUsername = findByUsername(newUsername);
        var userByEmail = findByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)) {
            var currentUser = findByUsername(currentUsername);
            if (currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXIST);
            }
            if (userByEmail != null && !currentUser.getId().equals(userByEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXIST);
            }
            return currentUser;
        } else {
            if (userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXIST);
            }
            if (userByEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXIST);
            }
            return null;
        }
    }
}
