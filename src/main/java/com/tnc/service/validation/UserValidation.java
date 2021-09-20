package com.tnc.service.validation;

import com.tnc.service.domain.UserDomain;
import com.tnc.service.exception.UsernameExistException;

public interface UserValidation {
    UserDomain validateNewUserName(UserDomain userDomain) throws UsernameExistException;
}
