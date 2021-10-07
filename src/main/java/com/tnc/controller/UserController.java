package com.tnc.controller;

import com.tnc.controller.dto.UserDTO;
import com.tnc.controller.mapper.UserDTOMapper;
import com.tnc.service.domain.HttpResponse;
import com.tnc.service.exception.*;
import com.tnc.service.interfaces.UserService;
import com.tnc.service.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.tnc.service.constant.FileConstant.*;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping(path = {"/", "/users"})
@AllArgsConstructor
//@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
public class UserController extends ExceptionHandling {

    public static final String EMAIL_SENT = "An email with a new password eas sent to: ";
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully. ";
    private final UserService userService;
    private final UserDTOMapper userDTOMapper;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        var loginUser = userService.login(userDTOMapper.toDomain(userDTO));
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = userService.getJwtHeader(userPrincipal);
        return new ResponseEntity<>(userDTOMapper.toDTO(loginUser), jwtHeader, OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) throws UserNotFoundException, EmailExistException, UsernameExistException, MessagingException {
        var user = userDTOMapper.toDTO(userService.register(userDTO.firstName(), userDTO.lastName(), userDTO.username(), userDTO.email()));
        return new ResponseEntity<>(user, OK);
    }

    @PostMapping("/add")
//    @Validated(OnCreate.class)
    public ResponseEntity<UserDTO> addNewUser(@RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("username") String username,
                                              @RequestParam("email") String email,
                                              @RequestParam("role") String role,
                                              @RequestParam("isActive") String isActive,
                                              @RequestParam("isNotLocked") String isNotLocked,
                                              @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws IOException {
        var newUser = userService.addNewUser(firstName, lastName, username, email, role,
                Boolean.parseBoolean(isNotLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(userDTOMapper.toDTO(newUser), OK);
    }

    @PostMapping("/update")
//    @Validated(OnCreate.class)
    public ResponseEntity<UserDTO> updateUser(@RequestParam("currentUsername") String currentUsername,
                                              @RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("username") String username,
                                              @RequestParam("email") String email,
                                              @RequestParam("role") String role,
                                              @RequestParam("isActive") String isActive,
                                              @RequestParam("isNotLocked") String isNotLocked,
                                              @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws UserNotFoundException, EmailExistException, IOException, UsernameExistException {
        var updateUser = userService.updateUser(currentUsername, firstName, lastName, username, email, role,
                Boolean.parseBoolean(isNotLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(userDTOMapper.toDTO(updateUser), OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username) {
        var userDomain = userService.findByUsername(username);
        return new ResponseEntity<>(userDTOMapper.toDTO(userDomain), OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userDTOMapper.toDTOList(userService.getAll());
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/resetPassword/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws EmailNotFoundException, MessagingException {
        userService.resetPassword(email);
        return response(OK, EMAIL_SENT + email);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return response(NO_CONTENT, USER_DELETED_SUCCESSFULLY);
    }

    @PostMapping("/updateProfileImage")
    public ResponseEntity<UserDTO> updateProfileImage(@RequestParam("username") String username,
                                                      @RequestParam(value = "profileImage") MultipartFile profileImage) throws UserNotFoundException, EmailExistException, IOException, UsernameExistException {
        var userDto = userDTOMapper.toDTO(userService.updateProfileImage(username, profileImage));
        return new ResponseEntity<>(userDto, OK);
    }

    @GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
    }

    @GetMapping(path = "/image/{profile}/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTemporaryProfileImage(@PathVariable("username") String username) throws IOException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
        var byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        HttpResponse body = new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<>(body, httpStatus);
    }
}
