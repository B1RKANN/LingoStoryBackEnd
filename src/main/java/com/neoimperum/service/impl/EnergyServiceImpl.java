package com.neoimperum.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoEnergy;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.Energy;
import com.neoimperum.model.User;
import com.neoimperum.repository.EnergyRepository;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.service.IEnergyService;

@Service
public class EnergyServiceImpl implements IEnergyService {

	@Autowired
	private EnergyRepository energyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private Long userIdToEnergyId(Long input) {
		Optional<User> optUser = userRepository.findById(input);
		if (optUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, ""));
		}
		return optUser.get().getEnergy().getId();
	}
	
	@Override
	public DtoEnergy findEnergyById(Long id) {
		DtoEnergy dtoEnergy = new DtoEnergy();
		Long energyId = userIdToEnergyId(id);
		Optional<Energy> optEnergy = energyRepository.findById(energyId);
		BeanUtils.copyProperties(optEnergy.get(), dtoEnergy);
		return dtoEnergy;
	}

	@Override
	public DtoEnergy reduceEnergy(Long id) {
		DtoEnergy dtoEnergy = new DtoEnergy();
		Long energyId = userIdToEnergyId(id);
		Optional<Energy> optEnergy = energyRepository.findById(energyId);
		byte newEnergy = optEnergy
			    .map(e -> e.getUserEnergy() < 10 ? e.getUserEnergy() : (byte) (e.getUserEnergy() - 10))
			    .orElse((byte) 0);

		Energy energy = new Energy();
		energy.setUserEnergy(newEnergy);
		energy.setId(energyId);
		Energy savedEnergy = energyRepository.save(energy);
		BeanUtils.copyProperties(savedEnergy, dtoEnergy);
		
		return dtoEnergy;
	}

}
