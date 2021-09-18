//package com.tnc.service.impl;
//
//import com.tnc.repository.entities.User;
//import com.tnc.repository.repositories.UserRepository;
//import com.tnc.service.interfaces.UserService;
//import com.tnc.service.model.UserDomain;
//import com.tnc.service.security.UserPrincipal;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Transactional
//@Qualifier("userDetailsService")
//@RequiredArgsConstructor
//public class UserServiceImplForJWT implements UserService, UserDetailsService {
//
//    private Logger LOGGER = LoggerFactory.getLogger(getClass()); //this class
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDomain get(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<UserDomain> getAll() {
//        return null;
//    }
//
//    @Override
//    public UserDomain add(UserDomain userDomain) {
//        return null;
//    }
//
//    @Override
//    public UserDomain update(UserDomain userDomain) {
//        return null;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findUserByUsername(username);
//        if (user == null) {
//            LOGGER.error("User not found by username " + username);
//            throw new UsernameNotFoundException("User not found by username " + username);
//        } else {
//            user.setLastLoginDateDisplay(user.getLastLoginDate());
//            user.setLastLoginDate(new Date());
//            userRepository.save(user);
//            UserPrincipal userPrincipal = new UserPrincipal(user);
//            LOGGER.info("Returnig found user by username " + username);
//            return userPrincipal;
//        }
//    }
//}
