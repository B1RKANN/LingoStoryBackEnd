package com.neoimperum.dto;

import java.util.ArrayList;
import java.util.List;

import com.neoimperum.enums.LevelStatus;

import lombok.Data;

@Data
public class DtoUser extends DtoBase  {
	private String username;
	
	private String password;
	
	private LevelStatus currentLevel;
	
}
