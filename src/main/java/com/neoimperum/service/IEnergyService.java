package com.neoimperum.service;

import com.neoimperum.dto.DtoEnergy;

public interface IEnergyService {
	
	public DtoEnergy findEnergyById(Long id);
	
	public DtoEnergy reduceEnergy(Long id);
	
}
