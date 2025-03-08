package com.neoimperum.service;

import java.util.List;

import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum1Step;

public interface IA1Bolum1StepService {
	
	public List<DtoA1Bolum1Step> findA1Bolum1StepById(Long id);
	
	public List<DtoA1Bolum1Step> saveStep(StepResponse stepResponse);
	
}
