package com.neoimperum.dto;

import java.util.ArrayList;
import java.util.List;

import com.neoimperum.enums.LevelStatus;

import lombok.Data;

@Data
public class DtoUserLevel extends DtoBase {

	private LevelStatus levelStatus;
	
	private List<DtoUserBolum> userBolum = new ArrayList<>();
	
}
