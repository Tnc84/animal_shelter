package com.tnc.service;

import com.tnc.repository.interfaces.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements ServiceInterface{

    private final AnimalRepository animalRepository;


    @Override
    public Object get() {
        return null;
    }
}
