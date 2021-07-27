//package com.tnc;
//
//import com.tnc.repository.animal.AnimalRepository;
//import com.tnc.service.mapper.AnimalDomainMapper;
//import com.tnc.service.model.AnimalDomain;
//import com.tnc.service.impl.AnimalServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class TestAnimalRepository {
//
//    private final AnimalDomain animalDomain
//            = new AnimalDomain(null, "Brien", "breed", "Dog", "https", null);
//    @Mock
//    private AnimalRepository animalRepository;
//    @Mock
//    private AnimalDomainMapper animalDomainMapper;
//    @InjectMocks
//    private AnimalServiceImpl animalService;
//
////    public void init() {
////        animalRepository = Mockito.mock(AnimalRepository.class);
////        animalDomainMapper = Mockito.mock(AnimalDomainMapper.class);
////        animalService = new AnimalServiceImpl(animalRepository, animalDomainMapper);
////    }
//
//    @Test
//    public void testAnimalCreate() {
////        init();
//        animalService.add(animalDomain);
//    }
//}
