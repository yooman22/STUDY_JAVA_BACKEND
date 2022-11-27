package com.example.API_PROJECT.data.dao;

import java.util.List;

import com.example.API_PROJECT.data.entity.UserEntity;

public interface UserDAO {
	
	UserEntity createUser(UserEntity userEntity);
	
	UserEntity getUser(String userId);
	
	List<UserEntity> getUserAll();
	
	String DeleteAccount(String id, String password);
	
	String Login(String id, String password);

}
