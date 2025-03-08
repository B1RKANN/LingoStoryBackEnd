package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum2Step;

public interface IA1Bolum2StepController {
    RootEntity<List<DtoA1Bolum2Step>> findA1Bolum2StepById(Long id);
    RootEntity<List<DtoA1Bolum2Step>> saveStep(StepResponse stepResponse);
}
