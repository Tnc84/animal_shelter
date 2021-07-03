package com.tnc.controller;

import com.tnc.controller.DTOMapper.AnimalDTOMapper;
import com.tnc.controller.dto.AnimalDTO;
import com.tnc.repository.animalRepository.Animal;
import com.tnc.service.animalService.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AnimalDTO>create(@RequestBody AnimalDTO animalDTO){
        var getOne = animalDTOMapper.toDomain(animalDTO);
        return ResponseEntity.ok(animalDTO);
    }

    @PutMapping
    public ResponseEntity<AnimalDTO>update(@RequestBody AnimalDTO animalDTO){
        var updateAnimal = animalService.update(animalDTOMapper.toDomain(animalDTO));
        return ResponseEntity.ok(animalDTOMapper.toDTO(updateAnimal));
    }
}
