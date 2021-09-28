package com.tnc.repository.repositories;

import com.tnc.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String laseName, String username, String email, String role, boolean isActive, boolean isNotActive, MultipartFile profileImage);

    User updateUser(String currentUsername, String newFirstName, String newLaseName, String newUsername, String newEmail, String role, boolean isActive, boolean isNotActive, MultipartFile profileImage);

    void deleteUser(Long id);

    void resetPassword(String email);

    User updateProfileImage(String username, MultipartFile profileImage);
}
