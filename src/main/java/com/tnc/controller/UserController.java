package com.tnc.controller;

import com.tnc.controller.dto.UserDTO;
import com.tnc.controller.mapper.UserDTOMapper;
import com.tnc.service.interfaces.UserService;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
//@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
public class UserController {

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    @GetMapping("/home")
    public String showUser(){
        return "Application works";
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO>get(@PathVariable Long id){
        return ResponseEntity.ok(userDTOMapper.toDTO(userService.get(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(userDTOMapper.toDTOList(userService.getAll()));
    }

    @PostMapping
    @Validated(OnCreate.class)
    public ResponseEntity<UserDTO>add(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userDTOMapper.toDTO(userService.add(userDTOMapper.toDomain(userDTO))));
    }

    @PutMapping
    @Validated(OnUpdate.class)
//    @PreAuthorize("principal.username.startsWith('animal')")
    public ResponseEntity<UserDTO>update(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userDTOMapper.toDTO(userService.add(userDTOMapper.toDomain(userDTO))));
    }
}
