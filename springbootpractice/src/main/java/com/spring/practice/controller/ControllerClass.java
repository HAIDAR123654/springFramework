package com.spring.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class ControllerClass {
	
	@GetMapping("/")
	public String home() {
		return "home1";
	}
	
	@GetMapping("/login")
	public String userLoginPage() {
		return "user_login";
	}
	
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register";
	}
	@PostMapping("/register")
	public String register(Model m,
			@RequestParam String name,
			@RequestParam String username, 
			@RequestParam String password) {
		m.addAttribute("name", name);
		System.out.println(name + " " + username + " " + password);
		m.addAttribute("RegUser",username);
		return "user_login";
	}
	@PostMapping("/userHomePage")
	public String userHomePage(Model m, String username, String password) {
		m.addAttribute("user",username);
		System.out.println(m.getAttribute("name"));
		m.addAttribute("loginedname", m.getAttribute("name"));
		return "userHome";
	}
	
	@GetMapping("/userProfile")
	public String userProfilePage(@ModelAttribute("user") String user, Model m) {
		if(user.equals(m.getAttribute("RegUser"))) {
			System.out.println(user);
			return "user_profile";
		}
		else{
			System.out.println("user name not matched");
			return "redirect:/login";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
