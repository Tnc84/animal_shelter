package com.tnc.service.security;

import com.tnc.repository.entities.Role;
import com.tnc.repository.entities.RoleEnum;
import com.tnc.repository.entities.User;
import com.tnc.repository.repositories.RoleRepository;
import com.tnc.repository.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userOptional.get());
    }

    @Bean
    private CommandLineRunner setUpDefaultUser() {
        return args -> {
            final String defaultEmail = "user";
            final String defaultPassword = "password";

            Role moderatorRole = roleRepository.findByRole(RoleEnum.ROLE_MOD).orElseGet(() -> {
                Role role = new Role().setRole(RoleEnum.ROLE_MOD);
                return roleRepository.save(role);
            });

            userRepository.findByEmail(defaultEmail).orElseGet(() -> {
                User user = new User().setEmail(defaultEmail)
                        .setPassword(passwordEncoder.encode(defaultPassword))
                        .setRoles(Collections.singleton(moderatorRole));
                return userRepository.save(user);
            });
        };
    }
}
