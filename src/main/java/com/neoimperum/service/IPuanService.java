package com.neoimperum.service;

import java.util.List;

import com.neoimperum.dto.DtoPuan;
import com.neoimperum.enums.EarnedPoints;

public interface IPuanService {
	
	public DtoPuan updatePuan(Long id,EarnedPoints pointsToAdd);
	
	public List<DtoPuan> getAllPuansByOrderDesc();
}
