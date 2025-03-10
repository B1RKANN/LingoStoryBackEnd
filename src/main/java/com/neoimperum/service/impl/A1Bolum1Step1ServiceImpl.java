package com.neoimperum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.model.level.step.A1Bolum1Step1;
import com.neoimperum.repository.A1Bolum1Step1Repository;
import com.neoimperum.service.IA1Bolum1Step1Service;

@Service
public class A1Bolum1Step1ServiceImpl implements IA1Bolum1Step1Service {

	@Autowired
	private A1Bolum1Step1Repository a1Bolum1Step1Repository;
	
	@Override
	public List<A1Bolum1Step1> findA1Bolum1Step1() {
		List<A1Bolum1Step1> all = a1Bolum1Step1Repository.findAll();
		return all;
	}

}
