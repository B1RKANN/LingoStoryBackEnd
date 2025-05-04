package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.DtoPuan;
import com.neoimperum.dto.DtoRequestUpdatePuan;

public interface IRestPuanController {
	
	public RootEntity<DtoPuan> updatePuan(DtoRequestUpdatePuan dtoRequestUpdatePuan);
	
	public RootEntity<List<DtoPuan>> getAllPuansByOrder();
	
}
