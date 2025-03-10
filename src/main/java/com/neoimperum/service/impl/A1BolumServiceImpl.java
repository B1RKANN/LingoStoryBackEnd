package com.neoimperum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.model.level.A1Bolum;
import com.neoimperum.repository.A1BolumRepository;
import com.neoimperum.service.IA1BolumService;

@Service
public class A1BolumServiceImpl implements IA1BolumService {
	
	@Autowired
	private A1BolumRepository a1BolumRepository;
	
	@Override
	public List<A1Bolum> findA1Bolum() {
		List<A1Bolum> all = a1BolumRepository.findAll();
		return all;
	}

	
	
}
