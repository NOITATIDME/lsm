package com.cos.lsm.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public String join(@Valid JoinReqDto dto, BindingResult bindingResult, Model model) { 
		System.out.println("에러사이즈 : " + bindingResult.getFieldErrors().size());
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("필드 : " + error.getField());
				System.out.println("메시지 : " + error.getDefaultMessage());
			}
			model.addAttribute("errorMap", errorMap);
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
	public String login(@Valid LoginReqDto dto, BindingResult bindingResult, Model model) {

			User userEntity =  userRepository.mLogin(dto.getUsername(), dto.getPassword());
			
			System.out.println("에러사이즈:" + bindingResult.getFieldErrors().size());
			
			if( bindingResult.hasErrors() ) {
				Map<String, String> errorMap = new HashMap<>();
				for(FieldError error : bindingResult.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
					System.out.println("필드:" + error.getField());
					System.out.println("메시지:" + error.getDefaultMessage());
				}
				model.addAttribute("errorMap", errorMap);
				return "error/error";
			} 



		
				session.setAttribute("principal", userEntity);
				return "redirect:/home";




	}

	
}
