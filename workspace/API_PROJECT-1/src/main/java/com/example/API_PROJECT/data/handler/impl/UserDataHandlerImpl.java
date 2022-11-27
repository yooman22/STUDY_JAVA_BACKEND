package com.example.API_PROJECT.data.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.API_PROJECT.data.dao.UserDAO;
import com.example.API_PROJECT.data.entity.UserEntity;
import com.example.API_PROJECT.data.handler.*;


@Service
@Transactional
public class UserDataHandlerImpl implements UserDataHandler {

	UserDAO userDAO;
	
	@Autowired
	public UserDataHandlerImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public UserEntity createUserEntity(String id, String password) {
		
		UserEntity userEntity = new UserEntity(id, password);
		
		return userDAO.createUser(userEntity);
	}

	@Override
	public UserEntity getUserEntity(String id) {

		return userDAO.getUser(id);
	}

	@Override
	public List<UserEntity> getUserAllEntity() {

		List<UserEntity> userEntityList = userDAO.getUserAll();
		
		return userEntityList;
	}

	@Override
	public String UserDelete(String id, String password) {
	
	   return userDAO.DeleteAccount(id, password);
	}

	@Override
	public String Login(String id, String password) {

		return userDAO.Login(id, password);
	}


}
