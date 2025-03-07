package com.neoimperum.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IRestEnergyController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoEnergy;
import com.neoimperum.service.IEnergyService;

@RestController
@RequestMapping("/rest/api/energy")
public class RestEnergyControllerImpl extends RestBaseController implements IRestEnergyController{
	
	@Autowired
	private IEnergyService energyService;

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoEnergy> findEnergyById(@PathVariable(name = "id") Long id) {
		return ok(energyService.findEnergyById(id));
	}

	@PostMapping("/reduceEnergy/{id}")
	@Override
	public RootEntity<DtoEnergy> reduceEnergy(@PathVariable(name = "id") Long id) {
		return ok(energyService.reduceEnergy(id));
	}

}
