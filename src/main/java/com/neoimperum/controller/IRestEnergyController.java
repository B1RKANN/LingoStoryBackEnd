package com.neoimperum.controller;

import com.neoimperum.dto.DtoEnergy;

public interface IRestEnergyController {
	public RootEntity<DtoEnergy> findEnergyById(Long id);
	
	public RootEntity<DtoEnergy> reduceEnergy(Long id);
}
