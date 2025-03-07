package com.neoimperum.dto;

import lombok.Data;

@Data
public class DtoUser extends DtoBase  {
	private String username;
	
	private String password;
	
	private DtoEnergy energy;
	
	private DtoPuan dtoPuan;
	
	private DtoUserLevel dtoUserLevel;
}
