package com.icesi.edu.Stiven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.service.impl.BusinessEntityService;

@RequestMapping("/api")
@RestController
public class BusinessentityRestController {
	
	@Autowired
	private BusinessEntityService businessD;
	
	@PostMapping("/businessRest/")
	public void addProduct(@RequestBody Businessentity bu) {
		businessD.save(bu);
	}
	
}
