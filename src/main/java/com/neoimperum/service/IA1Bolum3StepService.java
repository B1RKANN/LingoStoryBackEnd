package com.neoimperum.service;

import java.util.List;

import com.neoimperum.dto.DtoA1Bolum3Step;
import com.neoimperum.dto.StepResponse;

public interface IA1Bolum3StepService {
    List<DtoA1Bolum3Step> findA1Bolum3StepById(Long id);
    List<DtoA1Bolum3Step> saveStep(StepResponse stepResponse);
}
