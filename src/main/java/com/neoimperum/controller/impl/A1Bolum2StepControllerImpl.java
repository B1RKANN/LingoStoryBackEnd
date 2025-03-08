package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1Bolum2StepController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoA1Bolum2Step;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.service.IA1Bolum2StepService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/a1/bolum2")
public class A1Bolum2StepControllerImpl extends RestBaseController implements IA1Bolum2StepController {
	
	@Autowired
	private IA1Bolum2StepService a1Bolum2StepService;

	@GetMapping("/{id}")
	@Override
	public RootEntity<List<DtoA1Bolum2Step>> findA1Bolum2StepById(@PathVariable(name = "id")Long id) {
		return ok(a1Bolum2StepService.findA1Bolum2StepById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<List<DtoA1Bolum2Step>> saveStep(@Valid @RequestBody StepResponse stepResponse) {
		return ok(a1Bolum2StepService.saveStep(stepResponse));
	}
}
