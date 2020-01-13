package com.sns.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sns.prj.domain.TokenVO;
import com.sns.prj.service.TokenService;

@Controller
public class PostController {
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/post/detail/{postId}", method = RequestMethod.GET)
	public String loginView(@CookieValue(value = "accesstoken", required = false) String token, @PathVariable int postId, Model model) {
		model.addAttribute("id", postId);
		
		TokenVO tokenVo = null;
		if(token != null) {
			tokenVo = tokenService.getTokenByToken(token);
		}
		model.addAttribute("user", tokenVo);
		return "detail";
	}
}
