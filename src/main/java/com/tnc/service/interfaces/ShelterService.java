package com.tnc.service.interfaces;

import com.tnc.exceptions.ApiException;
import com.tnc.service.model.ShelterDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShelterService {
    ShelterDomain get(Long id);

    List<ShelterDomain> getAll();

    ShelterDomain add(ShelterDomain shelterDomain) throws ApiException;

    ShelterDomain update(ShelterDomain shelterDomain);
}
