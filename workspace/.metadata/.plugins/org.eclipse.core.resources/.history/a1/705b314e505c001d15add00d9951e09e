package com.example.API_PROJECT.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API_PROJECT.data.dto.UserDTO;
import com.example.API_PROJECT.data.entity.UserEntity;
import com.example.API_PROJECT.data.handler.UserDataHandler;
import com.example.API_PROJECT.data.dto.*;
import com.example.API_PROJECT.service.UserService;


//- Coupling의 정의
//coupling이란 서로 상호작용하는 시스템들간의 의존성을 의미한다. 
//의존성은 실질적 의존성과 인위적 의존성으로 나뉠 수 있다. 
//실질적 의존성은 한 시스템이 소비하는 다른 시스템의 기능이나 서비스 집합을 의미한다 
//인위적 의존성은 한 시스템이 다른 시스템이 제공하는 기능이나 서비스를 소비하기 위해 필요한 여러 요소들의 
//집합을 의미한다. 전형적으로 인위적 의존성은 언어적인 의존성, 플랫폼 의존성, API 의존성등이 있다. 
//인위적 의존성은 언제나 존재하지만 그 비용은 충분히 감소될 수 있다. Loose Coupling은 이러한 인위적 
//의존성을 최소한으로 줄이는 구조를 의미한다.

// 사용 이유 : 다른 서비스를 시작 할때 해당 interface 부분에 있는 함수를 사용

@Service
public class UserServiceImpl implements UserService {
	
	UserDataHandler userDataHandler;
	
	@Autowired
	public UserServiceImpl(UserDataHandler userDataHandler) {
		this.userDataHandler = userDataHandler;
	}
	
	@Override
	public String createUser(String id, String password, String Token) {
		
		UserEntity userEntity = userDataHandler.createUserEntity(id, password);
		
		UserDTO userDTO = new UserDTO(userEntity);
		
		return Token;
	}
	
	@Override
	public UserDTO getUser(String id) {
		
		UserEntity userEntity = userDataHandler.getUserEntity(id);
		
		UserDTO userDTO = new UserDTO(userEntity);
		
		return userDTO;
	}

	@Override
	public List<UserDTO> getUserALL() {
		
		List<UserEntity> userEntity = userDataHandler.getUserAllEntity();
		
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		
		for(int i = 0 ; i< userEntity.size(); i++) {
			userDTO.add(new UserDTO(userEntity.get(i)));
		}
		
		return userDTO;
	}

	@Override
	public String deleteAccount(String id, String password) {
		
		String result = userDataHandler.UserDelete(id, password);
		
		return result;
	}

	@Override
	public String Login(String id, String password) {
		
		return userDataHandler.Login(id, password);
	}

	@Override
	public UserDTO loadUserByUsername(String token) {
		
		return null;
	}
	
	
}
