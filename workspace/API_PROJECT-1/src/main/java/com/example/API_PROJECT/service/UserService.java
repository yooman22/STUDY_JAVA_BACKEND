package com.example.API_PROJECT.service;

import java.util.List;

import com.example.API_PROJECT.data.dto.*;

public interface UserService {

	String createUser(String id, String password , String Token);
	
	UserDTO getUser(String id);
	
	List<UserDTO> getUserALL();
	
	String deleteAccount(String id, String password);
	
	String Login(String id, String password);
	
	UserDTO loadUserByUsername(String token);
	
}
