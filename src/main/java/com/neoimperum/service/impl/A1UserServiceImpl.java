package com.neoimperum.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoA1User;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.level.A1User;
import com.neoimperum.repository.A1UserRepository;
import com.neoimperum.service.IA1UserService;

@Service
public class A1UserServiceImpl implements IA1UserService {
	
	@Autowired
	private A1UserRepository a1UserRepository;

	@Override
	public List<DtoA1User> findByA1Users(Long id) {
		List<DtoA1User> dtoA1UsersList = new ArrayList<>();
		List<A1User> users = a1UserRepository.findByUserId(id);
		
		if (users.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
        
        for (A1User user : users) {
            DtoA1User dtoA1User = new DtoA1User();
            BeanUtils.copyProperties(user, dtoA1User);
            dtoA1UsersList.add(dtoA1User);
        }
		
		return dtoA1UsersList;
	}
}
