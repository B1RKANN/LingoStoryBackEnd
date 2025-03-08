package com.neoimperum.service;

import java.util.List;

import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum2Step;

public interface IA1Bolum2StepService {
    List<DtoA1Bolum2Step> findA1Bolum2StepById(Long id);
    List<DtoA1Bolum2Step> saveStep(StepResponse stepResponse);
}
