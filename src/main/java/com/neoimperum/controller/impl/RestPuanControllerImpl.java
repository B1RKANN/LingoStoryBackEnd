package com.neoimperum.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IRestPuanController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoPuan;
import com.neoimperum.dto.DtoRequestUpdatePuan;
import com.neoimperum.enums.EarnedPoints;
import com.neoimperum.service.IPuanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/puan")
public class RestPuanControllerImpl extends RestBaseController implements IRestPuanController {

	@Autowired
	private IPuanService puanService;
	
	@PostMapping("/update")
	@Override
	public RootEntity<DtoPuan> updatePuan(@Valid @RequestBody DtoRequestUpdatePuan dtoRequestUpdatePuan) {
		return ok(puanService.updatePuan(dtoRequestUpdatePuan.getId(), dtoRequestUpdatePuan.getPointsToAdd()));
	}

}
