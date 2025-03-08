package com.neoimperum.service;

import java.util.List;

import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum5Step;

public interface IA1Bolum5StepService {
    List<DtoA1Bolum5Step> findA1Bolum5StepById(Long id);
    List<DtoA1Bolum5Step> saveStep(StepResponse stepResponse);
}
