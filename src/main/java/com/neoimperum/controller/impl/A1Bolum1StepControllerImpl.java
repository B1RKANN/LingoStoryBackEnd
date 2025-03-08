package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1Bolum1StepController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoA1Bolum1Step;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.service.IA1Bolum1StepService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/a1/bolum1")
public class A1Bolum1StepControllerImpl extends RestBaseController implements IA1Bolum1StepController {
	
	@Autowired
	private IA1Bolum1StepService a1Bolum1StepService;

	@GetMapping("/{id}")
	@Override
	public RootEntity<List<DtoA1Bolum1Step>> findA1Bolum1StepById(@PathVariable(name = "id")Long id) {
		return ok(a1Bolum1StepService.findA1Bolum1StepById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<List<DtoA1Bolum1Step>> saveStep(@Valid @RequestBody StepResponse stepResponse) {
		return ok(a1Bolum1StepService.saveStep(stepResponse));
	}

}
