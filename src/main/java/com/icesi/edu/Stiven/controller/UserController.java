package com.icesi.edu.Stiven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.icesi.edu.Stiven.service.inter.IUserService;


@Controller
public class UserController {

	/*private IUserService us;
	
	@Autowired
	public UserController(IUserService us ) {
		this.us= us;
	}*/
	
	@Autowired
	private BusinessDelegate us;
	
	@GetMapping("/users/")
	public String login(Model model) {
		model.addAttribute("users", us.showUserList());
		return "/users/indexUsers";
	}
	

}
