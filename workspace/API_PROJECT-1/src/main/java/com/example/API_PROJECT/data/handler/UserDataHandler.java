package com.example.API_PROJECT.data.handler;

import java.util.List;

import com.example.API_PROJECT.data.entity.UserEntity;

public interface UserDataHandler {
	
	UserEntity createUserEntity(String id , String password );
	
	UserEntity getUserEntity(String id);
	
	List<UserEntity> getUserAllEntity();
	
	String UserDelete(String id , String password);
	
	String Login(String id, String password);
	
}