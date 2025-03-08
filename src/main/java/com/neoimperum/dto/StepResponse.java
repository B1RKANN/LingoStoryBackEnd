package com.neoimperum.dto;

import com.neoimperum.enums.BolumPuanStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StepResponse {
	
	@NotNull
	private Long userId;
	
	@NotNull
	private BolumPuanStatus bolumPuanStatus;
	
	@NotNull
	private Long id;
}
