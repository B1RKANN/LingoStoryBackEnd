package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum3Step;

public interface IA1Bolum3StepController {
    RootEntity<List<DtoA1Bolum3Step>> findA1Bolum3StepById(Long id);
    RootEntity<List<DtoA1Bolum3Step>> saveStep(StepResponse stepResponse);
}
