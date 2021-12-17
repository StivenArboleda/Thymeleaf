package com.icesi.edu.Stiven.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.sales.Customer;
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
	
	@GetMapping("/personRest/listCustomers/{id}")
	public Iterable<Customer> showCustomersList(@PathVariable("id") Integer id) {
		return personD.findCustomers(id);
	}
	
	@PostMapping("/personRest/addperson/")
	public void addPerson(@RequestBody Person person) {
		personD.saveCorrect(person);
	}
	
	@DeleteMapping("/personRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		personD.deletePerson(personD.findbyId(id));
	}
	
	@GetMapping("/personRest/find/{id}")
	public Person viewPerson(@PathVariable("id") Integer id) {

		return personD.findbyId(id);
	}

	@PutMapping("/personRest/edit/{id}")
	public void editPerson(@RequestBody Person person) {

		personD.editPerson(person.getBusinessentityid(), person.getEmailpromotion(),
				person.getFirstname(), person.getLastname(), LocalDate.now(), person.getTitle());
	}
}
