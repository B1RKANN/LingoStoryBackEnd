package com.neoimperum.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.AuthRequest;
import com.neoimperum.dto.AuthRequest2;
import com.neoimperum.dto.AuthResponse;
import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.RefreshTokenRequest;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.jwt.JWTService;
import com.neoimperum.model.Energy;
import com.neoimperum.model.Puan;
import com.neoimperum.model.RefreshToken;
import com.neoimperum.model.User;
import com.neoimperum.model.UserBolum;
import com.neoimperum.repository.EnergyRepository;
import com.neoimperum.repository.PuanRepository;
import com.neoimperum.repository.RefreshTokenRepository;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private EnergyRepository energyRepository;
	
	@Autowired
	private PuanRepository puanRepository;
	
	private User createUser(AuthRequest2 input) {
		User user = new User();
		Energy energy = new Energy();
		Puan puan = new Puan();
		energy.setUserEnergy((byte) 30);
		Energy savedEnergy = energyRepository.save(energy);
		puan.setUserPuan(0);
		Puan savedPuan = puanRepository.save(puan);
		user.setEnergy(savedEnergy);
		user.setPuan(savedPuan);
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setCurrentLevel(input.getLevelStatus());
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredTime(new Date(System.currentTimeMillis()+1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	@Override
	public DtoUser register(AuthRequest2 input) {
		DtoUser dtoUser = new DtoUser();
		User savedUser = userRepository.save(createUser(input));
		
		BeanUtils.copyProperties(savedUser, dtoUser);
		
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
			
			authenticationProvider.authenticate(authenticationToken);
			
			Optional<User> optUser = userRepository.findByUsername(input.getUsername());
	
			String accesToken = jwtService.generateToken(optUser.get());
			
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));
			
			return new AuthResponse(accesToken, savedRefreshToken.getRefreshToken());
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
	}
	
	
	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}
	
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
		
		if (optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
		}
		if(!isValidRefreshToken(optRefreshToken.get().getExpiredTime())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));
		}
		
		User user = optRefreshToken.get().getUser();
		
		String accesToken = jwtService.generateToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
		
		
		
		return new AuthResponse(accesToken, savedRefreshToken.getRefreshToken());
	}

}
