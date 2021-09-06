package com.cos.lsm.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.lsm.domain.user.UserRepository;

@Controller
public class UserController {
	
	private UserRepository userRepository;
	private HttpSession session;
	
	// DI
	public UserController(UserRepository userRepository, HttpSession session) {
		this.userRepository = userRepository;
		this.session = session;
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
}
