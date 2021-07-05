package com.tnc.controller;

import com.tnc.controller.DTOMapper.AnimalDTOMapper;
import com.tnc.controller.dto.AnimalDTO;
import com.tnc.service.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animals")
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
    public ResponseEntity<AnimalDTO>add(@Valid @RequestBody AnimalDTO animalDTO){
        var addOne = animalService.add(animalDTOMapper.toDomain(animalDTO));
//        var getOne = animalService.add(animalDTOMapper.toDomain(animalDTO));
        return ResponseEntity.ok(animalDTOMapper.toDTO(addOne));
    }

    @PutMapping
    public ResponseEntity<AnimalDTO>update(@Valid @RequestBody AnimalDTO animalDTO){
        var updateAnimal = animalService.update(animalDTOMapper.toDomain(animalDTO));
        return ResponseEntity.ok(animalDTOMapper.toDTO(updateAnimal));
    }
}
