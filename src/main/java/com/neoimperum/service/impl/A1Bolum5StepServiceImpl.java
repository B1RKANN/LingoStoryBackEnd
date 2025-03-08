package com.neoimperum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoA1Bolum5Step;
import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.StepType;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.User;
import com.neoimperum.model.step.A1Bolum5Step;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.repository.a1.bolum5.step.A1Bolum5StepRepository;
import com.neoimperum.service.IA1Bolum5StepService;

@Service
public class A1Bolum5StepServiceImpl implements IA1Bolum5StepService {
    
    @Autowired
    private A1Bolum5StepRepository a1Bolum5StepRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private A1Bolum5Step createStep(Long id) {
        A1Bolum5Step a1Bolum5Step = new A1Bolum5Step();
        
        Optional<User> optUser = userRepository.findById(id);
        a1Bolum5Step.setUser(optUser.get());
        a1Bolum5Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum5Step.setCompletionStatus(CompletionStatus.NOW);
        a1Bolum5Step.setStepType(StepType.ONE);
        return a1Bolum5StepRepository.save(a1Bolum5Step);
    }

    @Override
    public List<DtoA1Bolum5Step> findA1Bolum5StepById(Long id) {
        List<DtoA1Bolum5Step> dtoA1Bolum5StepsList = new ArrayList<>();
        List<A1Bolum5Step> steps = a1Bolum5StepRepository.findByUserId(id);
    
        if (steps.isEmpty()) {
            DtoA1Bolum5Step dtoA1Bolum5Step = new DtoA1Bolum5Step();
            A1Bolum5Step savedStep = createStep(id);
            BeanUtils.copyProperties(savedStep, dtoA1Bolum5Step);
            dtoA1Bolum5Step.setId(savedStep.getId());
            
            dtoA1Bolum5StepsList.add(dtoA1Bolum5Step);
            
            return dtoA1Bolum5StepsList; 
        }
    
        for (A1Bolum5Step step : steps) {
            DtoA1Bolum5Step dtoStep = new DtoA1Bolum5Step();
        
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(step.getUser(), dtoUser);
            dtoStep.setId(step.getId());
            dtoStep.setStepType(step.getStepType());
            dtoStep.setCompletionStatus(step.getCompletionStatus());
            dtoStep.setBolumPuanStatus(step.getBolumPuanStatus());
            dtoA1Bolum5StepsList.add(dtoStep);
        }
    
        return dtoA1Bolum5StepsList;
    }

    private int getFindStep(Long id) {
        List<A1Bolum5Step> optStep = a1Bolum5StepRepository.findByUserId(id);
        int eb = -1;
        for (A1Bolum5Step a1Bolum5Step : optStep) {
            int a = a1Bolum5Step.getStepType().ordinal();
            if (eb < a) {
                eb = a;
            }
        }
        return eb;
    }

    @Override
    public List<DtoA1Bolum5Step> saveStep(StepResponse stepResponse) {
        List<DtoA1Bolum5Step> dtoA1Bolum5StepList = new ArrayList<>();
        Optional<A1Bolum5Step> DboptTopStep = a1Bolum5StepRepository.findById(stepResponse.getId());
        A1Bolum5Step optTopStep = DboptTopStep.get();
        optTopStep.setBolumPuanStatus(stepResponse.getBolumPuanStatus());
        optTopStep.setCompletionStatus(CompletionStatus.SUCCESSFUL);
        A1Bolum5Step savedStep = a1Bolum5StepRepository.save(optTopStep);
        int findStep = getFindStep(stepResponse.getUserId());
        DtoA1Bolum5Step dtoA1Bolum5Step = new DtoA1Bolum5Step();
        BeanUtils.copyProperties(savedStep, dtoA1Bolum5Step);
        
        dtoA1Bolum5StepList.add(dtoA1Bolum5Step);
        
        if (findStep != 4) {
            if (optTopStep.getStepType().ordinal() == findStep) {
                A1Bolum5Step newStep = newStep(stepResponse.getUserId(), findStep + 1);
                DtoA1Bolum5Step dtoA1Bolum5Step2 = new DtoA1Bolum5Step();
                BeanUtils.copyProperties(newStep, dtoA1Bolum5Step2);
                dtoA1Bolum5StepList.add(dtoA1Bolum5Step2);
            }
        }
        return dtoA1Bolum5StepList;
    }
    
    private A1Bolum5Step newStep(Long userId, int stepNo) {
        A1Bolum5Step a1Bolum5Step = new A1Bolum5Step();
        User user = new User();
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, userId.toString()));
        }
        BeanUtils.copyProperties(optUser.get(), user);
        a1Bolum5Step.setUser(user);
        a1Bolum5Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum5Step.setCompletionStatus(CompletionStatus.NOW);
        StepType step = StepType.values()[stepNo];
        a1Bolum5Step.setStepType(step);
        return a1Bolum5StepRepository.save(a1Bolum5Step);
    }
}
