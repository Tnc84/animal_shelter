package com.tnc.repository.repositories;

import com.tnc.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findUserByUsername(String username);
        Optional<User> findByEmail(String email);
}
