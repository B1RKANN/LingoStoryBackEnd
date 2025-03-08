package com.neoimperum.dto;

import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.LevelType;

import lombok.Data;

@Data
public class DtoA1User extends DtoBase {
	
	private LevelType levelType;
	
    private CompletionStatus completionStatus;
	
}
