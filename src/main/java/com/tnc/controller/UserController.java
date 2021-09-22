package com.tnc.controller;

import com.tnc.controller.dto.UserDTO;
import com.tnc.controller.dto.UserDTOForRegister;
import com.tnc.controller.mapper.UserDTOMapper;
import com.tnc.service.exception.EmailExistException;
import com.tnc.service.exception.ExceptionHandling;
import com.tnc.service.exception.UserNotFoundException;
import com.tnc.service.exception.UsernameExistException;
import com.tnc.service.interfaces.UserService;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = {"/", "/users"})
@AllArgsConstructor
//@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
public class UserController extends ExceptionHandling {

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    @PostMapping("/login")
    public ResponseEntity<UserDTOForRegister> login(@RequestBody UserDTOForRegister userDTO){
        return ResponseEntity.ok(userDTOMapper.toDTORegistration(userService.login(userDTOMapper.toDomainRegistration(userDTO))));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTOForRegister> register(@RequestBody UserDTOForRegister userDTO) throws UserNotFoundException, EmailExistException, UsernameExistException {
        return ResponseEntity.ok(userDTOMapper.toDTORegistration(userService.register(userDTOMapper.toDomainRegistration(userDTO))));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(userDTOMapper.toDTO(userService.get(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userDTOMapper.toDTOList(userService.getAll()));
    }

    @PostMapping
    @Validated(OnCreate.class)
    public ResponseEntity<UserDTO> add(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userDTOMapper.toDTO(userService.add(userDTOMapper.toDomain(userDTO))));
    }

    @PutMapping
    @Validated(OnUpdate.class)
//    @PreAuthorize("principal.username.startsWith('animal')")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userDTOMapper.toDTO(userService.add(userDTOMapper.toDomain(userDTO))));
    }
}
