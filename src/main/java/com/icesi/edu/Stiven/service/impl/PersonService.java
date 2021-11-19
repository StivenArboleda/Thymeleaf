package com.icesi.edu.Stiven.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.repositories.BusinessEntityRepository;
import com.icesi.edu.Stiven.repositories.PersonRepository;
import com.icesi.edu.Stiven.service.inter.IPersonService;

@Service
public class PersonService implements IPersonService{

	PersonRepository personR;
	
	private BusinessEntityRepository ber;
	
	public PersonService(PersonRepository personR, BusinessEntityRepository ber) {
		this.personR = personR;
		this.ber = ber;
	}
	
	public <S extends Person> S save(S person) {
		
		if(person.getFirstname().length() >= 3 && person.getLastname().length() >= 3 && person.getModifieddate() != null) {
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
		businessEntity.setModifieddate(Timestamp.from(Instant.now()));
		businessEntity = ber.save(businessEntity);
		person.setModifieddate(Timestamp.from(Instant.now()));
		
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
		businessEntity.setModifieddate(Timestamp.from(Instant.now()));
		businessEntity = ber.save(businessEntity);
		person.setModifieddate(Timestamp.from(Instant.now()));
		
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
	
	public <S extends Person> Iterable<S> saveAll(Iterable<S> persons) {
		for (Person person : persons) {
			save(person);
		}
		return persons;
	}
	
	public Optional<Person> findId(Integer id) {
		return personR.findById(id);
	}
	
	public boolean existsById(Integer id) {
		return personR.existsById(id);
	}
	
	public void editPerson(Integer businessentityid, Integer emailpromotion, String firstname,
			String lastname, Timestamp modifieddate, String title) {
		modifieddate = Timestamp.from(Instant.now());
		if(firstname.length() >= 3 && lastname.length() >= 3 && modifieddate != null) {
			
			Person pr = personR.findById(businessentityid).get();
			
			pr.setEmailpromotion(emailpromotion);
			pr.setFirstname(firstname);
			pr.setLastname(lastname);
			pr.setModifieddate(modifieddate);
			pr.setTitle(title);
			
			save(pr);
			
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
		return personR.findById(id).get();
	}
	
	public void deletePerson(Person p) {
		personR.delete(p);
	}
	
}
