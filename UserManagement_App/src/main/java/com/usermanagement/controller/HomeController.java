package com.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.usermanagement.entity.UserDtls;
import com.usermanagement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/signin")
	public String login() {
		return "signin";
	}
	
	@PostMapping("/login")
	public String userLoggedIn(@ModelAttribute UserDtls user, HttpSession session, Model model) {
		String username = user.getEmail();
		String password = user.getPassword();
		session.setAttribute("email", user.getEmail());
		UserDtls u = userService.fetchOneUser(username);
		model.addAttribute("userM",u);
		boolean a = userService.authenticateUser(username, password);
		
		if(a) {
			return "home";
		}
		else{
		    session.setAttribute("msg", "User name or password is incorrect");
		}
		return "redirect:/signin";
	}
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/createUser")
	public String createUser(@ModelAttribute UserDtls user, HttpSession session) {
		
		boolean f = userService.checkEmail(user.getEmail());
		
		if(f) {
			session.setAttribute("msg", "Email id already exists..");
		}
		else {
			UserDtls userDtls = userService.createUser(user);
			if(userDtls!=null) {
				session.setAttribute("msg","Registered Sucessfully..");
			}
			else {
				session.setAttribute("msg","Something wrong on server..");
			}
			
		}
		return "redirect:/register";
		
	}
	
	@GetMapping("/update")
	public String updateForm(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		UserDtls user = userService.fetchOneUser(email);
		model.addAttribute("modelM",user);
		return "UserUpdate";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute UserDtls user, HttpSession session) {
		
		boolean f = userService.checkEmail(user.getEmail());
		
		if(f) {
			session.setAttribute("msg", "Email id already exists..");
		}
		else {
			UserDtls userDtls = userService.createUser(user);
			if(userDtls!=null) {
				session.setAttribute("msg","updated Sucessfully..");
			}
			else {
				session.setAttribute("msg","Something wrong on server..");
			}
			return "redirect:/";
		}
		return "redirect:/update";
		
	}
}
