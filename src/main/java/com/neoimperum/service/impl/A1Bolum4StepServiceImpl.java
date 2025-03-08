package com.neoimperum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum4Step;
import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.LevelType;
import com.neoimperum.enums.StepType;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.User;
import com.neoimperum.model.level.A1User;
import com.neoimperum.model.step.A1Bolum4Step;
import com.neoimperum.repository.A1UserRepository;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.repository.a1.bolum4.step.A1Bolum4StepRepository;
import com.neoimperum.service.IA1Bolum4StepService;

@Service
public class A1Bolum4StepServiceImpl implements IA1Bolum4StepService {
    
    @Autowired
    private A1Bolum4StepRepository a1Bolum4StepRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private A1UserRepository a1UserRepository;
    
    private A1Bolum4Step createStep(Long id) {
        A1Bolum4Step a1Bolum4Step = new A1Bolum4Step();
        
        Optional<User> optUser = userRepository.findById(id);
        a1Bolum4Step.setUser(optUser.get());
        a1Bolum4Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum4Step.setCompletionStatus(CompletionStatus.NOW);
        a1Bolum4Step.setStepType(StepType.ONE);
        return a1Bolum4StepRepository.save(a1Bolum4Step);
    }

    @Override
    public List<DtoA1Bolum4Step> findA1Bolum4StepById(Long id) {
        List<DtoA1Bolum4Step> dtoA1Bolum4StepsList = new ArrayList<>();
        List<A1Bolum4Step> steps = a1Bolum4StepRepository.findByUserId(id);
    
        if (steps.isEmpty()) {
            DtoA1Bolum4Step dtoA1Bolum4Step = new DtoA1Bolum4Step();
            A1Bolum4Step savedStep = createStep(id);
            BeanUtils.copyProperties(savedStep, dtoA1Bolum4Step);
            dtoA1Bolum4Step.setId(savedStep.getId());
            
            dtoA1Bolum4StepsList.add(dtoA1Bolum4Step);
            
            return dtoA1Bolum4StepsList; 
        }
    
        for (A1Bolum4Step step : steps) {
            DtoA1Bolum4Step dtoStep = new DtoA1Bolum4Step();
        
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(step.getUser(), dtoUser);
            dtoStep.setId(step.getId());
            dtoStep.setStepType(step.getStepType());
            dtoStep.setCompletionStatus(step.getCompletionStatus());
            dtoStep.setBolumPuanStatus(step.getBolumPuanStatus());
            dtoA1Bolum4StepsList.add(dtoStep);
        }
    
        return dtoA1Bolum4StepsList;
    }

    private int getFindStep(Long id) {
        List<A1Bolum4Step> optStep = a1Bolum4StepRepository.findByUserId(id);
        int eb = -1;
        for (A1Bolum4Step a1Bolum4Step : optStep) {
            int a = a1Bolum4Step.getStepType().ordinal();
            if (eb < a) {
                eb = a;
            }
        }
        return eb;
    }

    @Override
    public List<DtoA1Bolum4Step> saveStep(StepResponse stepResponse) {
        List<DtoA1Bolum4Step> dtoA1Bolum4StepList = new ArrayList<>();
        Optional<A1Bolum4Step> DboptTopStep = a1Bolum4StepRepository.findById(stepResponse.getId());
        A1Bolum4Step optTopStep = DboptTopStep.get();
        optTopStep.setBolumPuanStatus(stepResponse.getBolumPuanStatus());
        optTopStep.setCompletionStatus(CompletionStatus.SUCCESSFUL);
        A1Bolum4Step savedStep = a1Bolum4StepRepository.save(optTopStep);
        int findStep = getFindStep(stepResponse.getUserId());
        DtoA1Bolum4Step dtoA1Bolum4Step = new DtoA1Bolum4Step();
        BeanUtils.copyProperties(savedStep, dtoA1Bolum4Step);
        
        dtoA1Bolum4StepList.add(dtoA1Bolum4Step);
        
        if (findStep != 4) {
            if (optTopStep.getStepType().ordinal() == findStep) {
                A1Bolum4Step newStep = newStep(stepResponse.getUserId(), findStep + 1);
                DtoA1Bolum4Step dtoA1Bolum4Step2 = new DtoA1Bolum4Step();
                BeanUtils.copyProperties(newStep, dtoA1Bolum4Step2);
                dtoA1Bolum4StepList.add(dtoA1Bolum4Step2);
            }
        }
        else {
            bolumNew(stepResponse.getUserId());
        }
        return dtoA1Bolum4StepList;
    }
    
    private A1Bolum4Step newStep(Long userId, int stepNo) {
        A1Bolum4Step a1Bolum4Step = new A1Bolum4Step();
        User user = new User();
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, userId.toString()));
        }
        BeanUtils.copyProperties(optUser.get(), user);
        a1Bolum4Step.setUser(user);
        a1Bolum4Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum4Step.setCompletionStatus(CompletionStatus.NOW);
        StepType step = StepType.values()[stepNo];
        a1Bolum4Step.setStepType(step);
        return a1Bolum4StepRepository.save(a1Bolum4Step);
    }

    private void bolumNew(Long userId) {
        A1User topA1User = a1UserRepository.findTopByUserIdOrderByIdDesc(userId);
        if (topA1User == null) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, userId.toString()));
        }
        topA1User.setCompletionStatus(CompletionStatus.SUCCESSFUL);
        a1UserRepository.save(topA1User);
        A1User a1User = new A1User();
        a1User.setUser(topA1User.getUser());
        a1User.setCompletionStatus(CompletionStatus.NOW);
        a1User.setLevelType(LevelType.FIVE);
        a1UserRepository.save(a1User);
    }
}
