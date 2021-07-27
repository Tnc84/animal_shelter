//package com.tnc;
//
//import com.tnc.repository.shelter.ShelterRepository;
//import com.tnc.service.mapper.ShelterDomainMapper;
//import com.tnc.service.model.AnimalDomain;
//import com.tnc.service.model.ShelterDomain;
//import com.tnc.service.impl.ShelterServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//
//@ExtendWith(MockitoExtension.class)
//public class TestShelterRepository {
//
//
//    private final ShelterDomain shelterDomain
//            = new ShelterDomain(null, "Copou ", "Iasi",
//            Collections.singletonList(new AnimalDomain(null, "Brien", "breed", "Dog", "https", null)));
//
//    @Mock
//    private ShelterRepository shelterRepository;
//
//    @Mock
//    private ShelterDomainMapper shelterDomainMapper;
//
//    @InjectMocks
//    private ShelterServiceImpl shelterService;
//
//    @Test
//    public void testShelterCreate() {
//        shelterService.add(shelterDomain);
//    }
//
//    @Test
//    public void testShelterGetAll() {
//        shelterService.getAll();
//    }
//}
