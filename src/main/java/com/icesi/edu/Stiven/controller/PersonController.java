package com.icesi.edu.Stiven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityService;
import com.icesi.edu.Stiven.service.inter.IPersonService;


@Controller
public class PersonController {

	//private IPersonService ps;
	//private IBusinessEntityService bes;
	
	private BusinessDelegate ps;
	
	/*@Autowired
	public PersonController(IPersonService ps, IBusinessEntityService bes) {
		this.ps = ps;
		this.bes = bes;
	}*/
	
	@Autowired
	public PersonController(BusinessDelegate ps) {
		this.ps = ps;
	}
	
	@GetMapping("/person/")
	public String index(Model model, Person p) {
		model.addAttribute("persons", ps.showPersonList());
		return "/persons/index";
	}
	
	@GetMapping("/searchPersons/{id}")
	public String search(Model model, @PathVariable Integer id) {
		
		model.addAttribute("person", ps.getFindByIdPerson(id));
		return "persons/searchPersons";
	}
	
	@GetMapping("/persons/searchPersonId")
	public String searchId() {
		return "persons/query";
	}
	
	@PostMapping("/persons/searchPersonId")
	public String searchId(@RequestParam("search") @PathVariable Integer id, Model model) {
					
		model.addAttribute("person", ps.getFindByIdPerson(id));
		return "persons/searchPersonId";
	}
	
	@GetMapping("/persons/customers/{id}")
    public String showCustomers(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("customers", ps.getCustomersByPerson(id));
        return "customers/indexToShow";
    }
	
	@GetMapping("/persons/addPersons")
	public String personsAdd(Model model) {
		
		Person p = new Person();
		Businessentity b = new Businessentity();
		p.setBusinessentity(b);
		
		model.addAttribute("person", p);

		
		return "persons/addPersons";
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
	}*/
	
	@PostMapping("/persons/addPersons")	
	public String addPersons(@ModelAttribute("person") Person person,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		//Businessentity be = new Businessentity();
		//be = person.getBusinessentity();
		//be = bes.save(be);
		//ps.saveEntity(be);
		//person.setBusinessentity(be);
		//ps.saveCorrect(person);
		ps.addPerson(person);
		return "redirect:/person/";
	}
	
	@PostMapping("/persons/updatePersons/{id}")
	public String updatePerson(Model model, @PathVariable Integer id, @ModelAttribute Person person,
			@RequestParam(value="action", required=true) String action) {
		
		//ps.editPerson(person.getBusinessentityid(), person.getEmailpromotion(), person.getFirstname(), 
			//	person.getLastname(), person.getModifieddate(), person.getTitle());
		ps.editPerson(person.getBusinessentityid(), person);
		
		
		return "redirect:/person/";
	}
	
	@GetMapping("/updatePersons/{id}")
	public String personUpdate(Model model, @PathVariable Integer id) {
		model.addAttribute("person", ps.getFindByIdPerson(id));
		
		return "persons/updatePersons";
	}
	
	@GetMapping("/persons/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {
		
		Person p = ps.getFindByIdPerson(id);
		ps.deletePerson(p);
		
		return "redirect:/person/";
	}

}
