package com.sns.prj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/login")
	public String loginView() {
		return "login";
	}

	@GetMapping("/signup")
	public String singupView() {
		return "signup";
	}

}
