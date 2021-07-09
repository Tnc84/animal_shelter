package com.tnc;

import com.tnc.repository.entity.Animal;
import com.tnc.repository.iRepository.AnimalRepository;
import com.tnc.service.model.AnimalDomain;
import com.tnc.service.serviceImpl.AnimalServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestAnimalRepository {

    private final AnimalDomain animalDomain = new AnimalDomain(null, "Tnc", "breed", "Dog", "https", null);
//    private final Animal animal = new Animal().setId(null).setName("Brayan")
    private AnimalRepository animalRepository;
    private AnimalServiceImpl animalServiceImpl;

    public void init() {
        animalRepository = Mockito.mock(AnimalRepository.class);
        animalServiceImpl = new AnimalServiceImpl(animalRepository);
    }

    @Test
    public void testAnimalCreate() {
        animalServiceImpl.add(animalDomain);
    }
}
