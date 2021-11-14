package com.icesi.edu.Stiven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.service.inter.IPersonService;


@Controller
public class PersonController {

	private IPersonService ps;

	@Autowired
	public PersonController(IPersonService ps) {
		this.ps = ps;
	}
	
	@GetMapping("/person/")
	public String index(Model model) {
		model.addAttribute("persons", ps.findAll());
		return "/persons/index";
	}
	
	@GetMapping("/updatePersons/{id}")
	public String addPerson(Model model, @PathVariable Integer id) {
		model.addAttribute("person", ps.findbyId(id));
		return "persons/updatePersons";
	}
	
	@PostMapping("/persons/updatePersons/{id}")
	public String updatePerson(Model model, @PathVariable Integer id, @ModelAttribute Person person,
			@RequestParam(value="action", required=true) String action) {
		
		ps.editPerson(person.getBusinessentityid(), person.getEmailpromotion(), person.getFirstname(), 
				person.getLastname(), person.getModifieddate(), person.getTitle());
		
		return "redirect:/person/";
	}
	
	@GetMapping("/persons/updatePersons/{id}")
	public String removePerson(Model model, @PathVariable Integer id) {
		
		Person p = ps.findbyId(id);
		ps.deletePerson(p);
		
		return "redirect:/person/";
	}
	
	@GetMapping("/addPerson/")
	public String addUser(Model model) {

		return "persons/addPerson";
	}
}
