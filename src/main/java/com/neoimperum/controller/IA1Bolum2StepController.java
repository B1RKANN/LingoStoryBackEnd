package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.DtoA1Bolum2Step;
import com.neoimperum.dto.StepResponse;

public interface IA1Bolum2StepController {
    RootEntity<List<DtoA1Bolum2Step>> findA1Bolum2StepById(Long id);
    RootEntity<List<DtoA1Bolum2Step>> saveStep(StepResponse stepResponse);
}
