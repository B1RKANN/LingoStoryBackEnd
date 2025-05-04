package com.neoimperum.dto;

import com.neoimperum.enums.EarnedPoints;

import lombok.Data;

@Data
public class DtoPuan extends DtoBase {
	
	private Integer userPuan;
	private String username;
	private Long userId;
	
}
