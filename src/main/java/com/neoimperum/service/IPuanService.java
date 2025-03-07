package com.neoimperum.service;

import com.neoimperum.dto.DtoPuan;
import com.neoimperum.enums.EarnedPoints;

public interface IPuanService {
	
	public DtoPuan updatePuan(Long id,EarnedPoints pointsToAdd);
	
}
