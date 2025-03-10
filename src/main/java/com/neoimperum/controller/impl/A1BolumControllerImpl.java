package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1BolumController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.model.level.A1Bolum;
import com.neoimperum.service.IA1BolumService;

@RestController
@RequestMapping("/rest/api/a1")
public class A1BolumControllerImpl extends RestBaseController implements IA1BolumController{
	
	@Autowired
	private IA1BolumService a1BolumService;

	@GetMapping("/getAll")
	@Override
	public RootEntity<List<A1Bolum>> findA1Bolum() {
		return ok(a1BolumService.findA1Bolum());
	}

}
