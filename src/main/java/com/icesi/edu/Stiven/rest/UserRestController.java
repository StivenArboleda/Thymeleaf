package com.icesi.edu.Stiven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.model.person.UserModel;
import com.icesi.edu.Stiven.service.impl.UserService;

@RequestMapping("/api")
@RestController
public class UserRestController {
	
	@Autowired
	private UserService us;
	
	@GetMapping("/userRest/list")
	public Iterable<UserModel> showUserList(){
		return us.findAll();
	}
}
