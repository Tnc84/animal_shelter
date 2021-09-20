package com.tnc.service.validation;

import com.tnc.service.domain.UserDomain;
import com.tnc.service.exception.UsernameExistException;

public class ValidateUser {

    public static UserDomain validateNewUserName(UserDomain newUser) throws UsernameExistException {

        var currentUser = new UserDomain();

        if (!newUser.getUsername().isEmpty()) {
            throw new UsernameExistException("Username already exist");
        }
        if (currentUser.getLastName().equals(newUser.getLastName()) && currentUser.getLastName().equals(newUser.getLastName())
                && currentUser.getEmail().equals(newUser.getEmail()) && newUser.getUsername().isEmpty()) {
            throw new UsernameExistException("This user is taken. Please change your username or email address.");
        }

        return newUser;
    }
}
