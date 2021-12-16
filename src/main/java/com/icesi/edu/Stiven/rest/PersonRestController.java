package com.icesi.edu.Stiven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.DAO.PersonDAO;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.service.impl.PersonService;

@RequestMapping("/api")
@RestController
public class PersonRestController {
	
	@Autowired
	private PersonService personD;
	
	@GetMapping("/personRest/list")
	public Iterable<Person> showPersonList() {
		return personD.findAll();
	}
	
	@PostMapping("/personRest/addperson/")
	public void addProduct(@RequestBody Person person) {
		personD.saveCorrect(person);
	}
	
	@DeleteMapping("/personRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		personD.deletePerson(personD.findbyId(id));
	}
	
	@GetMapping("/personRest/find/{id}")
	public Person viewProduct(@PathVariable("id") Integer id) {

		return personD.findbyId(id);
	}
	
}
