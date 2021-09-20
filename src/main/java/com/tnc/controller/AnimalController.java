package com.tnc.controller;

import com.tnc.controller.mapper.AnimalDTOMapper;
import com.tnc.controller.dto.AnimalDTO;
import com.tnc.service.interfaces.AnimalService;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animals")
@Validated
//@PreAuthorize("isAuthenticated() && hasRole('USER')")
public class AnimalController {

    private final AnimalService animalService;
    private final AnimalDTOMapper animalDTOMapper;

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<AnimalDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(animalDTOMapper.toDTO(animalService.get(id)));
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAll(){
        return ResponseEntity.ok(animalDTOMapper.toDTOList(animalService.getAll()));
    }

    @PostMapping
    @Validated(OnCreate.class)
    public ResponseEntity<AnimalDTO>add(@Valid @RequestBody AnimalDTO animalDTO){
        var addOne = animalService.add(animalDTOMapper.toDomain(animalDTO));
//        var getOne = animalService.add(animalDTOMapper.toDomain(animalDTO));
        return ResponseEntity.ok(animalDTOMapper.toDTO(addOne));
    }

    @PutMapping
    @Validated(OnUpdate.class)
    public ResponseEntity<AnimalDTO>update(@Valid @RequestBody AnimalDTO animalDTO){
        var updateAnimal = animalService.update(animalDTOMapper.toDomain(animalDTO));
        return ResponseEntity.ok(animalDTOMapper.toDTO(updateAnimal));
    }
}
