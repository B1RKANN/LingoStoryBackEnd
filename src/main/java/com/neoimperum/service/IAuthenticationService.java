package com.neoimperum.service;

import com.neoimperum.dto.AuthRequest;
import com.neoimperum.dto.AuthResponse;
import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.RefreshTokenRequest;
import com.neoimperum.model.User;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);//düzelt
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
	
	
}
