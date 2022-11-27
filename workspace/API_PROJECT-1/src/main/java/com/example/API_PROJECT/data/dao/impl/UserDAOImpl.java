package com.example.API_PROJECT.data.dao.impl;

import com.example.API_PROJECT.data.dao.UserDAO;
import com.example.API_PROJECT.data.dto.UserDTO;
import com.example.API_PROJECT.data.entity.UserEntity;
import com.example.API_PROJECT.data.repository.UserRepository;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.API_PROJECT.*;
import com.example.API_PROJECT.controller.UserController;
import com.example.API_PROJECT.SecretKey.*;

@Service
@Transactional
@Slf4j
public class UserDAOImpl implements UserDAO {

	UserRepository userRepository;
	
	@Autowired
	public UserDAOImpl(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@Override
	public UserEntity createUser(UserEntity userEntity) {
		
		userRepository.save(userEntity);
		
		return userEntity;
	}

	@Override
	public UserEntity getUser(String userId) {
		
		UserEntity userEntity = userRepository.getReferenceById(userId);
		
		return userEntity;
	}

	@Override
	public List<UserEntity> getUserAll() {
	
		List<UserEntity> userEntityList = userRepository.findAll();
		
		return userEntityList;
	}

	@Override
	public String DeleteAccount(String id, String password) {
		
		UserEntity userEntity = userRepository.getById(id);
		
		if(userEntity !=null)
		{
			userRepository.deleteById(id);
			return "OK";
		}
		else {
			return "FAIL";
		}
	}

	@Override
	public String Login(String id, String password) {
		
		UserEntity userEntity = userRepository.findId(id);
		
		
		
		if(userEntity !=null)
		{
			if(password.equals(userEntity.getDTO().getPassword()))
			{
				return "OK";
			}
			else {
				return "PFAIL";
			}
		}
		
		return "FAIL";
	}
	

    
}

