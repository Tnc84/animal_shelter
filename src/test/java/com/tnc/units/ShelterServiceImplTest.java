//package com.tnc.units;
//
//
//import com.tnc.repository.entities.Shelter;
//import com.tnc.repository.repositories.ShelterRepository;
//import com.tnc.service.impl.ShelterServiceImpl;
//import com.tnc.service.model.ShelterDomain;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//
//@ExtendWith(MockitoExtension.class)
//public class ShelterServiceImplTest {
////    @Spy
////    private ShelterDomainMapper shelterDomainMapper = Mappers.getMapper(ShelterDomainMapper.class);
//
//    public ShelterDomain shelterDomain = new ShelterDomain();
//    public Shelter shelterJPA;
//
//    @BeforeEach
//    public void setUp() {
//        this.shelterDomain = new ShelterDomain()
//                .setId(123L)
//                .setName("Visan")
//                .setCity("Iasi")
//                .setAnimals(Collections.emptyList());
//    }
//
//    @Test
//    public void validateShelter() {
//        var shelterService = new ShelterServiceImpl(null);
//        shelterService.validateShelter(shelterDomain);
//    }
//
//    @Test
//    public void createShelterAndSaveDB() {
//
//        var shelterRepositoryMock = Mockito.mock(ShelterRepository.class);
//
//        var shelterService = new ShelterServiceImpl(shelterRepositoryMock);
//
//        Mockito.when(shelterRepositoryMock.save(Mockito.any())).thenReturn(shelterJPA);
//
//        ShelterDomain savedShelter = shelterService.add(shelterDomain);
//
//    }
//}
