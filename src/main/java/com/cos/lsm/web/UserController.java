package com.cos.lsm.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.lsm.domain.user.User;
import com.cos.lsm.domain.user.UserRepository;
import com.cos.lsm.web.dto.JoinReqDto;
import com.cos.lsm.web.dto.LoginReqDto;

@Controller
public class UserController {
	
	private UserRepository userRepository;
	private HttpSession session;
	
	// DI
	public UserController(UserRepository userRepository, HttpSession session) {
		this.userRepository = userRepository;
		this.session = session;
	}
	
	@GetMapping({"/","/home"})
	public String home() {
		return "home";
	}
	
	@PostMapping("/join")
	public String join(JoinReqDto dto) { // username=love&password=1234&email=love@nate.com
		
		if(dto.getUsername() == null ||
			dto.getPassword() == null ||
			dto.getEmail() == null ||
			dto.getUsername().equals("")||
			dto.getPassword().equals("")||
			dto.getEmail().equals("")
			) {
			return "error/error";
		}
		
		userRepository.save(dto.toEntity());
		return "redirect:/loginForm"; // 리다이렉션 (300)
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	

	@PostMapping("login")
	public String login(LoginReqDto dto) {
		System.out.println(dto.getUsername());
		System.out.println(dto.getPassword());
		
		User userEntity = userRepository.mLogin(dto.getUsername(), dto.getPassword());
		
		if(userEntity == null) {
			return "reirect:/loginFrom";
		} else {
			
			session.setAttribute("principal", userEntity);
			return "redirect:/home";
		}

		
	}

	
}
