package com.neoimperum.dto;

import com.neoimperum.enums.LevelStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest2 {

	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotNull
	private LevelStatus levelStatus;
}
