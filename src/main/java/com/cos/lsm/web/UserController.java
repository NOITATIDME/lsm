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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.lsm.domain.user.User;
import com.cos.lsm.domain.user.UserRepository;
import com.cos.lsm.util.Script;
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
	public @ResponseBody String join(@Valid JoinReqDto dto, BindingResult bindingResult, Model model) { 
		System.out.println("에러사이즈 : " + bindingResult.getFieldErrors().size());
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("필드 : " + error.getField());
				System.out.println("메시지 : " + error.getDefaultMessage());
			}
			
			return Script.back(errorMap.toString());
		}
		
		userRepository.save(dto.toEntity());
		return Script.href("/loginForm");
	}

	@PostMapping("/login")
	public @ResponseBody String login(@Valid LoginReqDto dto, BindingResult bindingResult, Model model) {

			System.out.println("에러사이즈:" + bindingResult.getFieldErrors().size());
			
			if( bindingResult.hasErrors() ) {
				Map<String, String> errorMap = new HashMap<>();
				for(FieldError error : bindingResult.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
					System.out.println("필드:" + error.getField());
					System.out.println("메시지:" + error.getDefaultMessage());
				}
				
				return Script.back(errorMap.toString());
			} 
			
				User userEntity =  userRepository.mLogin(dto.getUsername(), dto.getPassword());

				if(userEntity == null) {
					return Script.back("아이디 혹은 비밀번호를 잘못 입력하였습니다. 다시 입력하세요");
				}else {
					session.setAttribute("principal", userEntity);
					return Script.href("/", "로그인 성공");					
				}

	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	
}
