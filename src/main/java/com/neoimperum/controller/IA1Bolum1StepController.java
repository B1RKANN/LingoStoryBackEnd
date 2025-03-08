package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.DtoA1Bolum1Step;
import com.neoimperum.dto.StepResponse;

public interface IA1Bolum1StepController {
	
	RootEntity<List<DtoA1Bolum1Step>> findA1Bolum1StepById(Long id);
	
	RootEntity<List<DtoA1Bolum1Step>> saveStep(StepResponse stepResponse);
	
}
