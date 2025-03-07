package com.neoimperum.service.impl;

import java.beans.beancontext.BeanContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoEnergy;
import com.neoimperum.dto.DtoPuan;
import com.neoimperum.dto.DtoUser;
import com.neoimperum.dto.DtoUserBolum;
import com.neoimperum.dto.DtoUserLevel;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.User;
import com.neoimperum.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	private User createUser(Long id) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
		User user = new User();
		BeanUtils.copyProperties(optUser, user);
		return user;
	}
	
	@Override
	public DtoUser getUserById(Long id) {
		DtoUser dtoUser = new DtoUser();
		DtoEnergy dtoEnergy = new DtoEnergy();
		DtoPuan dtoPuan = new DtoPuan();
		DtoUserLevel dtoUserLevel = new DtoUserLevel();
		DtoUserBolum dtoUserBolum = new DtoUserBolum();//daha sonra
		User user = createUser(id);
		
		BeanUtils.copyProperties(user.getEnergy(), dtoEnergy);
		dtoUser.setEnergy(dtoEnergy);
		
		BeanUtils.copyProperties(user.getPuan(), dtoPuan);
		dtoUser.setDtoPuan(dtoPuan);
		
		 List<DtoUserBolum> userbolumList = new ArrayList<>();
		
		for (DtoUserBolum userlevels : userbolumList) {
			
			
			
			
		}

		
		BeanUtils.copyProperties(user, dtoUser);
		
		
		return null;
	}

}
