package com.neoimperum.service;

import java.util.List;

import com.neoimperum.dto.DtoA1User;

public interface IA1UserService {

	public List<DtoA1User> findByA1Users(Long id);
	
}
