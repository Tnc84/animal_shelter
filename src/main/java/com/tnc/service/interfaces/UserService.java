package com.tnc.service.interfaces;

import com.tnc.service.domain.UserDomain;
import com.tnc.service.exception.EmailExistException;
import com.tnc.service.exception.UserNotFoundException;
import com.tnc.service.exception.UsernameExistException;
import com.tnc.service.security.UserPrincipal;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public interface UserService {

//    UserDomain register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException;

    UserDomain findByUsername(String username);

    UserDomain findByEmail(String email);

    UserDomain get(Long id);

    List<UserDomain> getAll();

    UserDomain add(UserDomain userDomain);

    UserDomain update(UserDomain userDomain);

    UserDomain register(UserDomain toDomain) throws UserNotFoundException, EmailExistException, UsernameExistException, MessagingException;

//    void authenticate(String username, String password);

    UserPrincipal returnForLoginMethod(UserDomain userDomain);

    HttpHeaders getJwtHeader(UserPrincipal userPrincipal);
}
