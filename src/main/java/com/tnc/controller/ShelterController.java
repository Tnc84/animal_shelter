package com.tnc.controller;

import com.tnc.controller.DTOMapper.ShelterDTOMapper;
import com.tnc.controller.dto.ShelterDTO;
import com.tnc.repository.shelter.Shelter;
import com.tnc.service.shelterService.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shelters")
public class ShelterController {

    private final ShelterService shelterService;
    private final ShelterDTOMapper shelterDTOMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ShelterDTO>get(@PathVariable Long id){
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.get(id)));
    }

    @GetMapping
    public ResponseEntity<List<ShelterDTO>> getAll(){
        return ResponseEntity.ok(shelterDTOMapper.toDTOList(shelterService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ShelterDTO>add(@RequestBody ShelterDTO shelterDTO){
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.add(shelterDTOMapper.toDomain(shelterDTO))));
    }

    @PutMapping
    public ResponseEntity<ShelterDTO>update(@RequestBody ShelterDTO shelterDTO){
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.add(shelterDTOMapper.toDomain(shelterDTO))));
    }

}
