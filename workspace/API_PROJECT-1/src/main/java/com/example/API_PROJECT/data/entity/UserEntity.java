package com.example.API_PROJECT.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

import com.example.API_PROJECT.data.dto.UserDTO;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="userinfo")
public class UserEntity {
	
    
//    public UserEntity(String id, String password) {
//        this.id = id;
//        this.password = password;
//    }
	
	private String name;
	
	private String phoneNumber;
	
	@Id
    private String id;
	
	@Column(name = "Password")
    private String password;

    public UserEntity(String id, String password) {
		this.id = id;
    	this.password = password;
	}

	private int age;
	
	public UserDTO getDTO() {
		
		UserDTO dto = new UserDTO(id,password);
		
		return dto;
	}

}
