package com.icesi.edu.Stiven.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.BusinessEntityDAO;
import com.icesi.edu.Stiven.DAO.PersonDAO;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.repositories.BusinessEntityRepository;
import com.icesi.edu.Stiven.repositories.PersonRepository;
import com.icesi.edu.Stiven.service.inter.IPersonService;

@Service
public class PersonService implements IPersonService{

	PersonDAO personR;
	
	private BusinessEntityDAO ber;
	
	public PersonService(PersonDAO personR, BusinessEntityDAO ber) {
		this.personR = personR;
		this.ber = ber;
	}
	
	public <S extends Person> S save(S person, Integer business) {
		
		Businessentity be = new Businessentity();
		be = ber.findById(business);
		
		if(person.getFirstname().length() >= 3 && person.getLastname().length() >= 3 && person.getModifieddate() != null) {
			person.setBusinessentity(be);
			personR.save(person);
			return person;
		}else if(person.getFirstname().length() < 3){
			throw new IllegalArgumentException("The name is too short");
		}else if(person.getLastname().length() < 3) {
			throw new IllegalArgumentException("The lastname is too short");
		}else if(person.getModifieddate() == null) {
			throw new IllegalArgumentException("It has no modification date");
		}
		return null;
		
	}
	
	public void saveCorrect(Person person) {
			
		Businessentity businessEntity = new Businessentity();
		businessEntity.setModifieddate(LocalDate.now());
		businessEntity = ber.save(businessEntity);
		person.setModifieddate(LocalDate.now());
		
		if(person.getFirstname().length() >= 3 && person.getLastname().length() >= 3 && person.getModifieddate() != null) {
			
			person.setBusinessentity(businessEntity);
			
			personR.save(person);
			
		}else if(person.getFirstname().length() < 3){
			throw new IllegalArgumentException("The name is too short");
		}else if(person.getLastname().length() < 3) {
			throw new IllegalArgumentException("The lastname is too short");
		}else if(person.getModifieddate() == null) {
			throw new IllegalArgumentException("It has no modification date");
		}
		
	}
	public Person saveForContact(Person person) {
		
		Businessentity businessEntity = new Businessentity();
		businessEntity.setModifieddate(LocalDate.now());
		businessEntity = ber.save(businessEntity);
		person.setModifieddate(LocalDate.now());
		
		if(person.getFirstname().length() >= 3 && person.getLastname().length() >= 3 && person.getModifieddate() != null) {
			
			person.setBusinessentity(businessEntity);
			
			personR.save(person);
			return person;
		}else if(person.getFirstname().length() < 3){
			throw new IllegalArgumentException("The name is too short");
		}else if(person.getLastname().length() < 3) {
			throw new IllegalArgumentException("The lastname is too short");
		}else if(person.getModifieddate() == null) {
			throw new IllegalArgumentException("It has no modification date");
		}
		return null;
	}
	

	public Person findId(Integer id) {
		return personR.findById(id);
	}
	
	/*public boolean existsById(Integer id) {
		return personR.existsById(id);
	}*/
	
	public void editPerson(Integer businessentityid, Integer emailpromotion, String firstname,
			String lastname, LocalDate modifieddate, String title) {
		modifieddate = LocalDate.now();
		if(firstname.length() >= 3 && lastname.length() >= 3 && modifieddate != null) {
			
			Businessentity be = new Businessentity();
			be = ber.findById(businessentityid);
			
			Person pr = personR.findById(businessentityid);
			
			pr.setEmailpromotion(emailpromotion);
			pr.setFirstname(firstname);
			pr.setLastname(lastname);
			pr.setModifieddate(modifieddate);
			pr.setTitle(title);
			pr.setBusinessentity(be);
			
			
			saveCorrect(pr);
			
		}else {
			throw new IllegalArgumentException("One of the arguments is invalid" + firstname +"/"+ lastname +"/" +modifieddate);
		}
	}

	@Override
	public Iterable<Person> findAll() {
		return personR.findAll();
	}

	@Override
	public Person findbyId(Integer id) {
		return personR.findById(id);
	}
	
	public Iterable<Customer> findCustomers(Integer id){
		return personR.findCustomers(id);
	}
	
	public void deletePerson(Person p) {
		personR.delete(p);
	}
	
}
