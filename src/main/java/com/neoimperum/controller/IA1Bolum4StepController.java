package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum4Step;

public interface IA1Bolum4StepController {
    RootEntity<List<DtoA1Bolum4Step>> findA1Bolum4StepById(Long id);
    RootEntity<List<DtoA1Bolum4Step>> saveStep(StepResponse stepResponse);
}
