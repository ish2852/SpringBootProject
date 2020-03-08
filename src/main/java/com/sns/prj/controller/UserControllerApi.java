package com.sns.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sns.prj.domain.FollowVO;
import com.sns.prj.domain.TokenVO;
import com.sns.prj.domain.UserVO;
import com.sns.prj.service.FollowService;
import com.sns.prj.service.TokenService;
import com.sns.prj.service.UserService;

@RestController
public class UserControllerApi {
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private FollowService followService;


	@GetMapping("/user")
	public Object userCookieCheck(UserVO vo) {
		UserVO user = userService.getUserById(vo.getId());
		return user;
	}

	@PostMapping("/user")
	public Object insertUser(@RequestBody UserVO vo) {
		userService.insertUserByUsernameAndPassword(vo.getUsername(), vo.getPassword());
		UserVO user = userService.getUserByUsernameAndPassword(vo.getUsername(), vo.getPassword());
		if(user.getCreatedAt() != null) {
			followService.insertFollow(user.getId(), user.getId());
		}
		return user;
	}

	@PostMapping("/auth")
	public Object userlogin(@RequestBody UserVO vo) {
		UserVO user = userService.getUserByUsernameAndPassword(vo.getUsername(), vo.getPassword());

		TokenVO tokenVO = null;
		if (user != null) {
			tokenService.insertTokenByUserId(user.getId());
			tokenVO = tokenService.getTokenByUserId(user.getId());
		}
		return tokenVO;
	}
	
	@PostMapping("/follow")
	public Object insertFollow(@CookieValue(value = "accesstoken", required = false) String token, @RequestBody FollowVO followVO) {
		int successBySql = 0;
		Long followeeId = 0L;
		Long followerId = 0L;
		TokenVO tokenVO = null;
		UserVO userVO = null;
		
		if (token != null && followVO != null) {
			tokenVO = tokenService.getTokenByToken(token);
			userVO = userService.getUserById(followVO.getFolloweeId());
		}
		
		if(tokenVO != null && userVO != null) {
			followeeId = userVO.getId();
			followerId = tokenVO.getUserId();
			successBySql =followService.insertFollow(followeeId, followerId);

		}
		
		return successBySql;
	}
	
	@DeleteMapping("/follow")
	public Object deleteFollow(@CookieValue(value = "accesstoken", required = false) String token, @RequestBody FollowVO followVO) {
		int successBySql = 0;
		Long followeeId = 0L;
		Long followerId = 0L;		
		TokenVO tokenVO = null;
		UserVO userVO = null;
		if (token != null && followVO != null) {
			tokenVO = tokenService.getTokenByToken(token);
			userVO = userService.getUserById(followVO.getFolloweeId());
		}
		
		if(tokenVO != null && userVO != null) {
			followeeId = followVO.getFolloweeId();
			followerId = tokenVO.getUserId();
			successBySql = followService.deleteFollowByFolloweeIdAndFollowerId(followeeId, followerId);
		}
		
		return successBySql;
	}
}
