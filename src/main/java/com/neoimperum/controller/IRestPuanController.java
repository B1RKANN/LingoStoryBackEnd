package com.neoimperum.controller;

import com.neoimperum.dto.DtoPuan;
import com.neoimperum.dto.DtoRequestUpdatePuan;

public interface IRestPuanController {
	
	public RootEntity<DtoPuan> updatePuan(DtoRequestUpdatePuan dtoRequestUpdatePuan);
	
}
