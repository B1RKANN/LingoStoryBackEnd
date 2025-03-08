package com.neoimperum.dto;

import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.StepType;
import com.neoimperum.enums.CompletionStatus;

import lombok.Data;
@Data
public class DtoUserBolum extends DtoBase{
	
	private StepType bolumType;
	
	private CompletionStatus completionStatus;
	
	private BolumPuanStatus bolumPuanStatus;
	
	
	
}
