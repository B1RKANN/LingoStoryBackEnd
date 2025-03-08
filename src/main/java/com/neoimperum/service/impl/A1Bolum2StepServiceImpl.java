package com.neoimperum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoA1Bolum2Step;
import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.StepType;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.User;
import com.neoimperum.model.step.A1Bolum2Step;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.repository.a1.bolum2.step.A1Bolum2StepRepository;
import com.neoimperum.service.IA1Bolum2StepService;

@Service
public class A1Bolum2StepServiceImpl implements IA1Bolum2StepService {
    
    @Autowired
    private A1Bolum2StepRepository a1Bolum2StepRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private A1Bolum2Step createStep(Long id) {
        A1Bolum2Step a1Bolum2Step = new A1Bolum2Step();
        
        Optional<User> optUser = userRepository.findById(id);
        a1Bolum2Step.setUser(optUser.get());
        a1Bolum2Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum2Step.setCompletionStatus(CompletionStatus.NOW);
        a1Bolum2Step.setStepType(StepType.ONE);
        return a1Bolum2StepRepository.save(a1Bolum2Step);
    }

    @Override
    public List<DtoA1Bolum2Step> findA1Bolum2StepById(Long id) {
        List<DtoA1Bolum2Step> dtoA1Bolum2StepsList = new ArrayList<>();
        List<A1Bolum2Step> steps = a1Bolum2StepRepository.findByUserId(id);
    
        if (steps.isEmpty()) {
            DtoA1Bolum2Step dtoA1Bolum2Step = new DtoA1Bolum2Step();
            A1Bolum2Step savedStep = createStep(id);
            BeanUtils.copyProperties(savedStep, dtoA1Bolum2Step);
            dtoA1Bolum2Step.setId(savedStep.getId());
            
            dtoA1Bolum2StepsList.add(dtoA1Bolum2Step);
            
            return dtoA1Bolum2StepsList; 
        }
    
        for (A1Bolum2Step step : steps) {
            DtoA1Bolum2Step dtoStep = new DtoA1Bolum2Step();
        
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(step.getUser(), dtoUser);
            dtoStep.setId(step.getId());
            dtoStep.setStepType(step.getStepType());
            dtoStep.setCompletionStatus(step.getCompletionStatus());
            dtoStep.setBolumPuanStatus(step.getBolumPuanStatus());
            dtoA1Bolum2StepsList.add(dtoStep);
        }
    
        return dtoA1Bolum2StepsList;
    }

    private int getFindStep(Long id) {
        List<A1Bolum2Step> optStep = a1Bolum2StepRepository.findByUserId(id);
        int eb = -1;
        for (A1Bolum2Step a1Bolum2Step : optStep) {
            int a = a1Bolum2Step.getStepType().ordinal();
            if (eb < a) {
                eb = a;
            }
        }
        return eb;
    }

    @Override
    public List<DtoA1Bolum2Step> saveStep(StepResponse stepResponse) {
        List<DtoA1Bolum2Step> dtoA1Bolum2StepList = new ArrayList<>();
        Optional<A1Bolum2Step> DboptTopStep = a1Bolum2StepRepository.findById(stepResponse.getId());
        A1Bolum2Step optTopStep = DboptTopStep.get();
        optTopStep.setBolumPuanStatus(stepResponse.getBolumPuanStatus());
        optTopStep.setCompletionStatus(CompletionStatus.SUCCESSFUL);
        A1Bolum2Step savedStep = a1Bolum2StepRepository.save(optTopStep);
        int findStep = getFindStep(stepResponse.getUserId());
        DtoA1Bolum2Step dtoA1Bolum2Step = new DtoA1Bolum2Step();
        BeanUtils.copyProperties(savedStep, dtoA1Bolum2Step);
        
        dtoA1Bolum2StepList.add(dtoA1Bolum2Step);
        
        if (findStep != 4) {
            if (optTopStep.getStepType().ordinal() == findStep) {
                A1Bolum2Step newStep = newStep(stepResponse.getUserId(), findStep + 1);
                DtoA1Bolum2Step dtoA1Bolum2Step2 = new DtoA1Bolum2Step();
                BeanUtils.copyProperties(newStep, dtoA1Bolum2Step2);
                dtoA1Bolum2StepList.add(dtoA1Bolum2Step2);
            }
        }
        return dtoA1Bolum2StepList;
    }
    
    private A1Bolum2Step newStep(Long userId, int stepNo) {
        A1Bolum2Step a1Bolum2Step = new A1Bolum2Step();
        User user = new User();
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, userId.toString()));
        }
        BeanUtils.copyProperties(optUser.get(), user);
        a1Bolum2Step.setUser(user);
        a1Bolum2Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum2Step.setCompletionStatus(CompletionStatus.NOW);
        StepType step = StepType.values()[stepNo];
        a1Bolum2Step.setStepType(step);
        return a1Bolum2StepRepository.save(a1Bolum2Step);
    }
}
