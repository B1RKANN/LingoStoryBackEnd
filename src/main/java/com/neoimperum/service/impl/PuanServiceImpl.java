package com.neoimperum.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoimperum.dto.DtoPuan;
import com.neoimperum.enums.EarnedPoints;
import com.neoimperum.exception.BaseException;
import com.neoimperum.exception.ErrorMessage;
import com.neoimperum.exception.MessageType;
import com.neoimperum.model.Puan;
import com.neoimperum.model.User;
import com.neoimperum.repository.PuanRepository;
import com.neoimperum.repository.UserRepository;
import com.neoimperum.service.IPuanService;

@Service
public class PuanServiceImpl implements IPuanService {

	@Autowired
	private PuanRepository puanRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private Long userIdToPuanId(Long input) {
		Optional<User> optUser = userRepository.findById(input);
		if (optUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, input.toString()));
		}
		return optUser.get().getPuan().getId();
	}
	
	private Puan savePuan(Long id,EarnedPoints pointsToAdd) {
		Puan puan = new Puan();
		Long puanId = userIdToPuanId(id);
		Optional<Puan> optPuan = puanRepository.findById(puanId);
		if (optPuan.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, puanId.toString()));
		}
		int newPuan = pointsToAdd.ordinal() + optPuan.get().getUserPuan();
		puan.setId(puanId);
		puan.setUserPuan(newPuan);
		return puanRepository.save(puan);
	}
	
	@Override
	public DtoPuan updatePuan(Long id,EarnedPoints pointsToAdd) {
		DtoPuan dtoPuan = new DtoPuan();
		Puan savedPuan = savePuan(id, pointsToAdd);
		BeanUtils.copyProperties(savedPuan, dtoPuan);
		return dtoPuan;
	}

	@Override
	public List<DtoPuan> getAllPuansByOrderDesc() {
		List<Object[]> puanListWithUserInfo = puanRepository.findAllPuansWithUserInfoOrderByUserPuanDesc();
		return puanListWithUserInfo.stream()
				.map(objArray -> {
					String username = (String) objArray[0];
					Long userId = (Long) objArray[1];
					Puan puan = (Puan) objArray[2];
					
					DtoPuan dtoPuan = new DtoPuan();
					BeanUtils.copyProperties(puan, dtoPuan);
					dtoPuan.setUsername(username);
					dtoPuan.setUserId(userId);
					return dtoPuan;
				})
				.collect(Collectors.toList());
	}
}
