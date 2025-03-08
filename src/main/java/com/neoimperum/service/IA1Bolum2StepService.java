package com.neoimperum.service;

import java.util.List;

import com.neoimperum.dto.DtoA1Bolum2Step;
import com.neoimperum.dto.StepResponse;

public interface IA1Bolum2StepService {
    List<DtoA1Bolum2Step> findA1Bolum2StepById(Long id);
    List<DtoA1Bolum2Step> saveStep(StepResponse stepResponse);
}
