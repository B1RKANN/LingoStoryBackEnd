package com.neoimperum.dto;

import com.neoimperum.enums.EarnedPoints;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoRequestUpdatePuan {

	@NotNull
	private Long id;
	
	@NotNull
	private EarnedPoints pointsToAdd;
	
}
