package com.neoimperum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.StepResponse;
import com.neoimperum.dto.step.DtoA1Bolum3Step;
import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.LevelType;
import com.neoimperum.enums.StepType;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.User;
import com.neoimperum.model.level.A1User;
import com.neoimperum.model.step.A1Bolum3Step;
import com.neoimperum.repository.A1UserRepository;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.repository.a1.bolum3.step.A1Bolum3StepRepository;
import com.neoimperum.service.IA1Bolum3StepService;

@Service
public class A1Bolum3StepServiceImpl implements IA1Bolum3StepService {
    
    @Autowired
    private A1Bolum3StepRepository a1Bolum3StepRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private A1UserRepository a1UserRepository;
    
    private A1Bolum3Step createStep(Long id) {
        A1Bolum3Step a1Bolum3Step = new A1Bolum3Step();
        
        Optional<User> optUser = userRepository.findById(id);
        a1Bolum3Step.setUser(optUser.get());
        a1Bolum3Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum3Step.setCompletionStatus(CompletionStatus.NOW);
        a1Bolum3Step.setStepType(StepType.ONE);
        return a1Bolum3StepRepository.save(a1Bolum3Step);
    }

    @Override
    public List<DtoA1Bolum3Step> findA1Bolum3StepById(Long id) {
        List<DtoA1Bolum3Step> dtoA1Bolum3StepsList = new ArrayList<>();
        List<A1Bolum3Step> steps = a1Bolum3StepRepository.findByUserId(id);
    
        if (steps.isEmpty()) {
            DtoA1Bolum3Step dtoA1Bolum3Step = new DtoA1Bolum3Step();
            A1Bolum3Step savedStep = createStep(id);
            BeanUtils.copyProperties(savedStep, dtoA1Bolum3Step);
            dtoA1Bolum3Step.setId(savedStep.getId());
            
            dtoA1Bolum3StepsList.add(dtoA1Bolum3Step);
            
            return dtoA1Bolum3StepsList; 
        }
    
        for (A1Bolum3Step step : steps) {
            DtoA1Bolum3Step dtoStep = new DtoA1Bolum3Step();
        
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(step.getUser(), dtoUser);
            dtoStep.setId(step.getId());
            dtoStep.setStepType(step.getStepType());
            dtoStep.setCompletionStatus(step.getCompletionStatus());
            dtoStep.setBolumPuanStatus(step.getBolumPuanStatus());
            dtoA1Bolum3StepsList.add(dtoStep);
        }
    
        return dtoA1Bolum3StepsList;
    }

    private int getFindStep(Long id) {
        List<A1Bolum3Step> optStep = a1Bolum3StepRepository.findByUserId(id);
        int eb = -1;
        for (A1Bolum3Step a1Bolum3Step : optStep) {
            int a = a1Bolum3Step.getStepType().ordinal();
            if (eb < a) {
                eb = a;
            }
        }
        return eb;
    }

    @Override
    public List<DtoA1Bolum3Step> saveStep(StepResponse stepResponse) {
        List<DtoA1Bolum3Step> dtoA1Bolum3StepList = new ArrayList<>();
        Optional<A1Bolum3Step> DboptTopStep = a1Bolum3StepRepository.findById(stepResponse.getId());
        A1Bolum3Step optTopStep = DboptTopStep.get();
        optTopStep.setBolumPuanStatus(stepResponse.getBolumPuanStatus());
        optTopStep.setCompletionStatus(CompletionStatus.SUCCESSFUL);
        A1Bolum3Step savedStep = a1Bolum3StepRepository.save(optTopStep);
        int findStep = getFindStep(stepResponse.getUserId());
        DtoA1Bolum3Step dtoA1Bolum3Step = new DtoA1Bolum3Step();
        BeanUtils.copyProperties(savedStep, dtoA1Bolum3Step);
        
        dtoA1Bolum3StepList.add(dtoA1Bolum3Step);
        
        if (findStep != 4) {
            if (optTopStep.getStepType().ordinal() == findStep) {
                A1Bolum3Step newStep = newStep(stepResponse.getUserId(), findStep + 1);
                DtoA1Bolum3Step dtoA1Bolum3Step2 = new DtoA1Bolum3Step();
                BeanUtils.copyProperties(newStep, dtoA1Bolum3Step2);
                dtoA1Bolum3StepList.add(dtoA1Bolum3Step2);
            }
        }
        else {
            bolumNew(stepResponse.getUserId());
        }
        return dtoA1Bolum3StepList;
    }
    
    private A1Bolum3Step newStep(Long userId, int stepNo) {
        A1Bolum3Step a1Bolum3Step = new A1Bolum3Step();
        User user = new User();
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, userId.toString()));
        }
        BeanUtils.copyProperties(optUser.get(), user);
        a1Bolum3Step.setUser(user);
        a1Bolum3Step.setBolumPuanStatus(BolumPuanStatus.ZERO);
        a1Bolum3Step.setCompletionStatus(CompletionStatus.NOW);
        StepType step = StepType.values()[stepNo];
        a1Bolum3Step.setStepType(step);
        return a1Bolum3StepRepository.save(a1Bolum3Step);
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
        a1User.setLevelType(LevelType.FOUR);
        a1UserRepository.save(a1User);
    }
}
