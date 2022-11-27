package com.example.API_PROJECT.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.API_PROJECT.data.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

//	@Query(value = "SELECT t.Id , t.Password from UserEntity t WHERE t.Id = : userId ")
//	UserEntity findId(@Param("userId") String userId);	
	
	@Query("select u from UserEntity u where u.id = :userId")
    UserEntity findId(@Param("userId") String userId);
}
