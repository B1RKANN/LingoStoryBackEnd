package com.neoimperum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum1Step;
import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.LevelType;
import com.neoimperum.enums.StepType;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.User;
import com.neoimperum.model.level.A1User;
import com.neoimperum.model.step.A1Bolum1Step;
import com.neoimperum.repository.A1UserRepository;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.repository.a1.bolum1.step.A1Bolum1StepRepository;
import com.neoimperum.service.IA1Bolum1StepService;

@Service
public class A1Bolum1StepServiceImpl implements IA1Bolum1StepService{
	
	@Autowired
	private A1Bolum1StepRepository a1Bolum1StepRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private A1UserRepository a1UserRepository;
	
	private A1Bolum1Step createStep(Long id) {
		A1Bolum1Step a1Bolum1Step = new A1Bolum1Step();
		
		Optional<User> optUser = userRepository.findById(id);
		a1Bolum1Step.setUser(optUser.get());
		a1Bolum1Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
		a1Bolum1Step.setCompletionStatus(CompletionStatus.NOW);
		a1Bolum1Step.setStepType(StepType.ONE);
		return a1Bolum1StepRepository.save(a1Bolum1Step);
	}

	@Override
	public List<DtoA1Bolum1Step> findA1Bolum1StepById(Long id) {
		List<DtoA1Bolum1Step> dtoA1Bolum1StepsList = new ArrayList<>();
		List<A1Bolum1Step> steps = a1Bolum1StepRepository.findByUserId(id);
    
		if (steps.isEmpty()) {
			DtoA1Bolum1Step dtoA1Bolum1Step = new DtoA1Bolum1Step();
			A1Bolum1Step savedStep = createStep(id);
			BeanUtils.copyProperties(savedStep, dtoA1Bolum1Step);
			dtoA1Bolum1Step.setId(savedStep.getId());
			
			dtoA1Bolum1StepsList.add(dtoA1Bolum1Step);
			
			return dtoA1Bolum1StepsList; 
		}
    
		for (A1Bolum1Step step : steps) {
			DtoA1Bolum1Step dtoStep = new DtoA1Bolum1Step();
        
			DtoUser dtoUser = new DtoUser();
			BeanUtils.copyProperties(step.getUser(), dtoUser);
			dtoStep.setId(step.getId());
			dtoStep.setStepType(step.getStepType());
			dtoStep.setCompletionStatus(step.getCompletionStatus());
			dtoStep.setBolumPuanStatus(step.getBolumPuanStatus());
			dtoA1Bolum1StepsList.add(dtoStep);
		}
    
		return dtoA1Bolum1StepsList;
	}
	
	private int getFindStep(Long id){
		List<A1Bolum1Step> optStep = a1Bolum1StepRepository.findByUserId(id);
		int eb = -1;
		for (A1Bolum1Step a1Bolum1Step : optStep) {
			int a = a1Bolum1Step.getStepType().ordinal();
			if (eb<a) {
				eb = a;
			}
		}
		return eb;
	}

	@Override
	public List<DtoA1Bolum1Step> saveStep(StepResponse stepResponse) {
		List<DtoA1Bolum1Step> dtoA1Bolum1StepList = new ArrayList<>();
		Optional<A1Bolum1Step> DboptTopStep = a1Bolum1StepRepository.findById(stepResponse.getId());
		A1Bolum1Step optTopStep = DboptTopStep.get();
		optTopStep.setBolumPuanStatus(stepResponse.getBolumPuanStatus());
		optTopStep.setCompletionStatus(CompletionStatus.SUCCESSFUL);
		A1Bolum1Step savedStep = a1Bolum1StepRepository.save(optTopStep);
		int findStep = getFindStep(stepResponse.getUserId());
		DtoA1Bolum1Step dtoA1Bolum1Step = new DtoA1Bolum1Step();
		BeanUtils.copyProperties(savedStep, dtoA1Bolum1Step);
		
		dtoA1Bolum1StepList.add(dtoA1Bolum1Step);
		
		if (findStep!=4) {
			if (optTopStep.getStepType().ordinal()==findStep) {
				A1Bolum1Step newStep = newStep(stepResponse.getUserId(), findStep+1);
				DtoA1Bolum1Step dtoA1Bolum1Step2 = new DtoA1Bolum1Step();
				BeanUtils.copyProperties(newStep, dtoA1Bolum1Step2);
				dtoA1Bolum1StepList.add(dtoA1Bolum1Step2);
			}
		}
		else {
			bolumNew(stepResponse.getUserId());
		}
		return dtoA1Bolum1StepList;
	}
	
	private A1Bolum1Step newStep(Long userId,int stepNo) {
		A1Bolum1Step a1Bolum1Step = new A1Bolum1Step();
		User user = new User();
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, userId.toString()));
		}
		BeanUtils.copyProperties(optUser.get(), user);
		a1Bolum1Step.setUser(user);
		a1Bolum1Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
		a1Bolum1Step.setCompletionStatus(CompletionStatus.NOW);
		StepType step = StepType.values()[stepNo];
		a1Bolum1Step.setStepType(step);
		return a1Bolum1StepRepository.save(a1Bolum1Step);
	}
	
	private void bolumNew(Long userId) {
		A1User topA1User = a1UserRepository.findTopByUserIdOrderByIdDesc(userId);
		if (topA1User==null) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, userId.toString()));
		}
		topA1User.setCompletionStatus(CompletionStatus.SUCCESSFUL);
		a1UserRepository.save(topA1User);
		A1User a1User = new A1User();
		a1User.setUser(topA1User.getUser());
		a1User.setCompletionStatus(CompletionStatus.NOW);
		a1User.setLevelType(LevelType.TWO);
		a1UserRepository.save(a1User);
	}
	
	

}
