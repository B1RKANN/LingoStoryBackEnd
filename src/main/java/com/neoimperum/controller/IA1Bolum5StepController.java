package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.DtoA1Bolum5Step;
import com.neoimperum.dto.StepResponse;

public interface IA1Bolum5StepController {
    RootEntity<List<DtoA1Bolum5Step>> findA1Bolum5StepById(Long id);
    RootEntity<List<DtoA1Bolum5Step>> saveStep(StepResponse stepResponse);
}
