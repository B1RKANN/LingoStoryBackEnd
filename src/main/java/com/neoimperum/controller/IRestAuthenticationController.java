package com.neoimperum.controller;

import com.neoimperum.dto.AuthRequest;
import com.neoimperum.dto.AuthRequest2;
import com.neoimperum.dto.AuthResponse;
import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest2 input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);


}
