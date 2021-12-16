package com.icesi.edu.Stiven.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.DAO.AddressDAO;
import com.icesi.edu.Stiven.DAO.AddressTypeDAO;
import com.icesi.edu.Stiven.DAO.BusinessEntityAddressDAO;
import com.icesi.edu.Stiven.DAO.BusinessEntityDAO;
import com.icesi.edu.Stiven.DAO.PersonDAO;
import com.icesi.edu.Stiven.DAO.PersonPhoneDAO;
import com.icesi.edu.Stiven.DAO.StateProvinceDAO;
import com.icesi.edu.Stiven.model.person.Person;


@RestController
@RequestMapping("/api")
public class ControllerRest {
	
	PersonDAO person;
	BusinessEntityDAO business;
	BusinessEntityAddressDAO businessAddress;
	StateProvinceDAO state;
	AddressDAO addres;
	AddressTypeDAO addressType;
	PersonPhoneDAO personPhone;
	
	@Autowired
	public ControllerRest(PersonDAO person, BusinessEntityDAO business, BusinessEntityAddressDAO businessAddress,
			StateProvinceDAO state, AddressDAO addres, AddressTypeDAO addressType, PersonPhoneDAO personPhone) {
		this.person = person;
		this.business = business;
		this.businessAddress = businessAddress;
		this.state = state;
		this.addres = addres;
		this.addressType = addressType;
		this.personPhone = personPhone;
	}
	
	//PEERSON
	
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPerson(@PathVariable(value = "id") Integer id) {
		Person pe = person.findById(id);
		return new ResponseEntity<Person>(pe, HttpStatus.OK);
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersons() {
		List<Person> persons = ((List<Person>) person.findAll());
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/persons/title={title}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByTitle(@PathVariable(value = "title") String title) {
		List<Person> persons = ((List<Person>) person.findByTitle(title));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/persons/date1={date1}/date2={date2}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByDate(@PathVariable(value = "date1") String date1,
			@PathVariable(value = "date2") String date2) {
		List<Person> persons = ((List<Person>) person.findByDateInterval(date1, date2));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public ResponseEntity<Person> createPerson(@RequestBody Person pe) {
		person.save(pe);
		return new ResponseEntity<Person>(pe, HttpStatus.CREATED);
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person pe) {

		person.update(pe);
		return ResponseEntity.ok(pe);
	}
}
