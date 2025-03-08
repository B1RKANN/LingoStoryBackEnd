package com.neoimperum.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IA1UserController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.DtoA1User;
import com.neoimperum.service.IA1UserService;

@RestController
@RequestMapping("/rest/api/a1")
public class A1UserControllerImpl extends RestBaseController implements IA1UserController {
	
	@Autowired
	private IA1UserService a1UserService;

	@GetMapping("/{id}")
	@Override
	public RootEntity<List<DtoA1User>> findByA1Users(@PathVariable(name = "id") Long id) {
		return ok(a1UserService.findByA1Users(id));
	}

}
