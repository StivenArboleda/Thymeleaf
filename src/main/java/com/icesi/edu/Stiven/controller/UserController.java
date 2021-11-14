package com.icesi.edu.Stiven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.icesi.edu.Stiven.service.impl.UserService;
import com.icesi.edu.Stiven.service.inter.IUserService;


@Controller
public class UserController {

	private IUserService us;
	
	@Autowired
	public UserController(IUserService us ) {
		this.us= us;
	}
	
	@GetMapping("/users/")
	public String login(Model model) {
		model.addAttribute("users", us.findAll());
		return "/users/indexUsers";
	}
	
	@GetMapping("/user/addUsers")
	public String index(Model model) {
		//model.addAttribute("users", us.findAll());
		return "/users/addUsers";
	}
}
