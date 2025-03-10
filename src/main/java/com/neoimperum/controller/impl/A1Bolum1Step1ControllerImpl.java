package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1Bolum1Step1Controller;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.model.level.step.A1Bolum1Step1;
import com.neoimperum.service.IA1Bolum1Step1Service;

@RestController
@RequestMapping("/rest/api/a1/bolum1")
public class A1Bolum1Step1ControllerImpl extends RestBaseController implements IA1Bolum1Step1Controller{
	
	@Autowired
	private IA1Bolum1Step1Service aBolum1Step1Service;

	@GetMapping("/step1")
	@Override
	public RootEntity<List<A1Bolum1Step1>> findA1Bolum1Step1() {
		return ok(aBolum1Step1Service.findA1Bolum1Step1());
	}

}
