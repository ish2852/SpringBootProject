package com.sns.prj.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sns.prj.domain.TokenVO;
import com.sns.prj.service.TokenService;

@Controller
public class HomeController {
	@Autowired
	private TokenService tokenService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String home(@CookieValue(value = "accesstoken", required = false) String token, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		TokenVO tokenVo = null;
		if(token != null) {
			tokenVo = tokenService.getTokenByToken(token);
		}
		model.addAttribute("user", tokenVo );
		return "index";
	}
	

}