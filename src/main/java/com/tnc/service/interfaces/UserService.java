package com.tnc.service.interfaces;

import com.tnc.service.domain.UserDomain;
import com.tnc.service.exception.EmailExistException;
import com.tnc.service.exception.EmailNotFoundException;
import com.tnc.service.exception.UserNotFoundException;
import com.tnc.service.exception.UsernameExistException;
import com.tnc.service.security.UserPrincipal;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public interface UserService {

    UserDomain register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException, MessagingException;

    UserDomain findByUsername(String username);

    UserDomain findByEmail(String email);

    UserDomain addNewUser(String firstName, String laseName, String username, String email, String role, boolean isActive, boolean isNotActive, MultipartFile profileImage) throws IOException;

    UserDomain updateUser(String currentUsername, String newFirstName, String newLaseName, String newUsername, String newEmail, String role, boolean isActive, boolean isNotActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;

    void deleteUser(Long id);

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    UserDomain updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;

    UserPrincipal returnForLoginMethod(UserDomain userDomain);

    HttpHeaders getJwtHeader(UserPrincipal userPrincipal);

    List<UserDomain> getAll();

    UserDomain login(UserDomain userDomain);
}
