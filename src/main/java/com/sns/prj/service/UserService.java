package com.sns.prj.service;

import com.sns.prj.domain.UserVO;

public interface UserService {

	UserVO getUserByUsernameAndPassword(String username, String password);

	UserVO getUserById(Long long1);

	int insertUserByUsernameAndPassword(String username, String password);
}
