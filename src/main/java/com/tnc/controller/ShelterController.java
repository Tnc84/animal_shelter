package com.tnc.controller;

import com.tnc.controller.DTOMapper.ShelterDTOMapper;
import com.tnc.controller.dto.ShelterDTO;
import com.tnc.service.service.ShelterService;
import com.tnc.service.validation.OnCreate;
import com.tnc.service.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shelters")
@Validated
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
    @Validated(OnCreate.class)
    public ResponseEntity<ShelterDTO>add(@Valid @RequestBody ShelterDTO shelterDTO){
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.add(shelterDTOMapper.toDomain(shelterDTO))));
    }

    @PutMapping
    @Validated(OnUpdate.class)
    public ResponseEntity<ShelterDTO>update(@Valid @RequestBody ShelterDTO shelterDTO){
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.add(shelterDTOMapper.toDomain(shelterDTO))));
    }

}
