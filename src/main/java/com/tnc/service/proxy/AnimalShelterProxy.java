package com.tnc.service.proxy;

import com.tnc.controller.dto.ShelterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-management")
public interface AnimalShelterProxy {

    @GetMapping("/user-management/users")
    ResponseEntity<List<ShelterDTO>> getAll();
}
