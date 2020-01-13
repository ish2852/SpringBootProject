package com.sns.prj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.prj.domain.UserVO;
import com.sns.prj.repository.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDOA;

	@Override
	public UserVO getUserByUsernameAndPassword(String username, String password) {
		return userDOA.findByUsernameAndPassword(username, password);
	}

	@Override
	public UserVO getUserById(Long id) {
		return userDOA.findById(id);
	}

	@Override
	public int insertUserByUsernameAndPassword(String username, String password) {
		int successBySql = 0;
		
		UserVO user = new UserVO();
		user.setUsername(username);
		user.setPassword(password);
		userDOA.save(user);
		
		successBySql = 1;
		return successBySql;
	}
}
