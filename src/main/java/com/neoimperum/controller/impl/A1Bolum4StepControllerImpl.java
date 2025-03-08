package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1Bolum4StepController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoA1Bolum4Step;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.service.IA1Bolum4StepService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/a1/bolum4")
public class A1Bolum4StepControllerImpl extends RestBaseController implements IA1Bolum4StepController {
	
	@Autowired
	private IA1Bolum4StepService a1Bolum4StepService;

	@GetMapping("/{id}")
	@Override
	public RootEntity<List<DtoA1Bolum4Step>> findA1Bolum4StepById(@PathVariable(name = "id")Long id) {
		return ok(a1Bolum4StepService.findA1Bolum4StepById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<List<DtoA1Bolum4Step>> saveStep(@Valid @RequestBody StepResponse stepResponse) {
		return ok(a1Bolum4StepService.saveStep(stepResponse));
	}
}
