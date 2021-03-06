package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		Authentication authentication = securityContext.getAuthentication();
//		if (authentication != null && authentication.getPrincipal() instanceof User) {
//			User u= (User) authentication.getPrincipal();
//			name+=" from : "+u.getUsername();
//		}

		List<String> books=new ArrayList<>(); 
		books.add(name);
		model.addAttribute("books", books);
		return "greeting";
	}
	
	
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
}
