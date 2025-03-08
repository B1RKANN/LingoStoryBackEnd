package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1Bolum3StepController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoA1Bolum3Step;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.service.IA1Bolum3StepService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/a1/bolum3")
public class A1Bolum3StepControllerImpl extends RestBaseController implements IA1Bolum3StepController {
	
	@Autowired
	private IA1Bolum3StepService a1Bolum3StepService;

	@GetMapping("/{id}")
	@Override
	public RootEntity<List<DtoA1Bolum3Step>> findA1Bolum3StepById(@PathVariable(name = "id")Long id) {
		return ok(a1Bolum3StepService.findA1Bolum3StepById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<List<DtoA1Bolum3Step>> saveStep(@Valid @RequestBody StepResponse stepResponse) {
		return ok(a1Bolum3StepService.saveStep(stepResponse));
	}
}
