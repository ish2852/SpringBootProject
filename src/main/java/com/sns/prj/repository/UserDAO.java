package com.sns.prj.repository;

import org.springframework.data.repository.CrudRepository;

import com.sns.prj.domain.UserVO;

public interface UserDAO extends CrudRepository<UserVO, String>{

	UserVO findByUsernameAndPassword(String username, String password);

	UserVO findById(Long id);
	
}
