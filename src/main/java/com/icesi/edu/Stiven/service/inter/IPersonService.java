package com.icesi.edu.Stiven.service.inter;

import java.sql.Timestamp;


import com.icesi.edu.Stiven.model.person.Person;

public interface IPersonService {
	
	public void saveCorrect(Person person);
	
	public Iterable<Person> findAll();
	
	public Person findbyId(Integer id);
	
	public void editPerson(Integer businessentityid, Integer emailpromotion, String firstname,
			String lastname, Timestamp modifieddate, String title);
	
	public void deletePerson(Person p);
		
}
