package com.tnc.controller;

import com.tnc.controller.DTOMapper.ShelterDTOMapper;
import com.tnc.controller.dto.ShelterDTO;
import com.tnc.service.shelterService.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shelters")
public class ShelterController {

    private final ShelterService shelterService;
    private final ShelterDTOMapper shelterDTOMapper;

    @GetMapping
    public ResponseEntity<List<ShelterDTO>> getAll(){
        return ResponseEntity.ok(shelterDTOMapper.toDTOList(shelterService.getAll()));
    }
}
