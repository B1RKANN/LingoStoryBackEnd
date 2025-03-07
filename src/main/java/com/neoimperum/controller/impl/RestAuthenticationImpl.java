package com.neoimperum.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neoimperum.controller.IRestAuthenticationController;
import com.neoimperum.controller.RestBaseController;
import com.neoimperum.controller.RootEntity;
import com.neoimperum.dto.AuthRequest;
import com.neoimperum.dto.AuthResponse;
import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.RefreshTokenRequest;
import com.neoimperum.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationImpl extends RestBaseController implements IRestAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
		// TODO Auto-generated method stub
		return ok(authenticationService.register(input));
	}


	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
		// TODO Auto-generated method stub
		return ok(authenticationService.authenticate(input));
	}

	
	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
		return ok(authenticationService.refreshToken(input));
	}

}
