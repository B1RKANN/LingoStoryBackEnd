package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1Bolum5StepController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoA1Bolum5Step;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.service.IA1Bolum5StepService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/a1/bolum5")
public class A1Bolum5StepControllerImpl extends RestBaseController implements IA1Bolum5StepController {
	
	@Autowired
	private IA1Bolum5StepService a1Bolum5StepService;

	@GetMapping("/{id}")
	@Override
	public RootEntity<List<DtoA1Bolum5Step>> findA1Bolum5StepById(@PathVariable(name = "id")Long id) {
		return ok(a1Bolum5StepService.findA1Bolum5StepById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<List<DtoA1Bolum5Step>> saveStep(@Valid @RequestBody StepResponse stepResponse) {
		return ok(a1Bolum5StepService.saveStep(stepResponse));
	}
}
