package com.icesi.edu.Stiven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.service.inter.IAddressService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityService;
import com.icesi.edu.Stiven.service.inter.IPersonService;
import com.icesi.edu.Stiven.service.inter.IStateProvinceService;


@Controller
public class provinceAddressController {

	private IStateProvinceService ps;
	private IAddressService as;
	
	@Autowired
	public provinceAddressController(IStateProvinceService ps, IAddressService as) {
		this.ps = ps;
		this.as = as;
	}
	
	@GetMapping("/provinceAddress/")
	public String index(Model model) {
		model.addAttribute("addresses", as.findAll());
		return "/provinceAddress/index";
	}
	
	@GetMapping("/provinceAddress/addAddress")
	public String personsAdd(Model model) {
		
		Address address = new Address();
		
		model.addAttribute("address", address);

		
		return "provinceAddress/addAddress";
	}
	
	/*@PostMapping("/persons/addPersons")	
	public String addPersons(@ModelAttribute("person") Person person,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		Businessentity be = new Businessentity();
		be = person.getBusinessentity();
		be = bes.save(be);
		person.setBusinessentity(be);
		ps.saveCorrect(person);
		
		return "redirect:/person/";
	}
	
	
	@PostMapping("/persons/updatePersons/{id}")
	public String updatePerson(Model model, @PathVariable Integer id, @ModelAttribute Person person,
			@RequestParam(value="action", required=true) String action) {
		
		ps.editPerson(person.getBusinessentityid(), person.getEmailpromotion(), person.getFirstname(), 
				person.getLastname(), person.getModifieddate(), person.getTitle());
		
		return "redirect:/person/";
	}
	
	@GetMapping("/updatePersons/{id}")
	public String personUpdate(Model model, @PathVariable Integer id) {
		model.addAttribute("person", ps.findbyId(id));
		
		return "persons/updatePersons";
	}*/
	
	@GetMapping("/provinceAddress/addAddress/{id}")
	public String delete(Model model, @PathVariable Integer id) {

		as.deletebyId(id);
		
		return "redirect:/provinceAddress/";
	}
	
}
