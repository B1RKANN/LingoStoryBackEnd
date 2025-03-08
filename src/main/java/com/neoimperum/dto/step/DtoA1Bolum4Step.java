package com.neoimperum.dto.step;

import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.StepType;

import lombok.Data;

@Data
public class DtoA1Bolum4Step {
    private Long id;
    private StepType stepType;
    private CompletionStatus completionStatus;
    private BolumPuanStatus bolumPuanStatus;
}
