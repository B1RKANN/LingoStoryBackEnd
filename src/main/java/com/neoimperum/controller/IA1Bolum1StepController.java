package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum1Step;

public interface IA1Bolum1StepController {
	
	RootEntity<List<DtoA1Bolum1Step>> findA1Bolum1StepById(Long id);
	
	RootEntity<List<DtoA1Bolum1Step>> saveStep(StepResponse stepResponse);
	
}
