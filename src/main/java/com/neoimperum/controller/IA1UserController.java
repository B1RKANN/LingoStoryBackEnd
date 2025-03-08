package com.neoimperum.controller;

import java.util.List;

import com.neoimperum.dto.DtoA1User;

public interface IA1UserController {
	
	RootEntity<List<DtoA1User>> findByA1Users(Long id);
	
}
