package com.neoimperum.dto;

import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.StepType;
import com.neoimperum.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class DtoA1Bolum1Step extends DtoBase {

	private StepType stepType;
	
	private CompletionStatus completionStatus;
	    
	private BolumPuanStatus bolumPuanStatus;
	
}
