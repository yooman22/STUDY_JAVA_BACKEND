package com.example.API_PROJECT.data.dto;

import com.example.API_PROJECT.data.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class UserDTO {
	private String id; // EMAIL
	private String password;

    public UserDTO(UserEntity userEntity) {
    	this.id = userEntity.getId();
    	this.password = userEntity.getPassword();
    }

	public UserEntity toEntity() {
		return UserEntity.builder()
				.id(id)
				.password(password)
				.build();
	}



}
