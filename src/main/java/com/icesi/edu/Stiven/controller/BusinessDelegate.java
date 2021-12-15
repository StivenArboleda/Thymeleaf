package com.icesi.edu.Stiven.controller;

import org.springframework.http.HttpHeaders;
import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;


@Component
public class BusinessDelegate {
	
	@Autowired
	private static RestTemplate template;
	

	//PERSON
	

	public Person findbyId(Integer id) {
		String url = "http://localhost:8080/persons/" + id;
		Person pers = template.getForObject(url, Person.class);
		return pers;
	}

	public Iterable<Person> findAll() {
		String url = "http://localhost:8080/persons";
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public String addPerson(Person p) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(p, headers);

		return template.exchange("http://localhost:8080/persons", HttpMethod.POST, entity, String.class).getBody();
	}

	public String editPerson(Integer id, Person pe) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(pe, headers);

		return template.exchange("http://localhost:8080/persons/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	//BUSINESS ENTITY
	
	public Businessentity getEntity(Integer id) {
		String url = "http://localhost:8080/entities/" + id;
		Businessentity be = template.getForObject(url, Businessentity.class);
		return be;
	}

	public Iterable<Businessentity> getAllEntities() {
		String url = "http://localhost:8080/entities";
		Businessentity[] be = template.getForObject(url, Businessentity[].class);
		return Arrays.asList(be);
	}

	public String saveEntity(Businessentity be) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Businessentity> entity = new HttpEntity<Businessentity>(be, headers);

		return template.exchange("http://localhost:8080/entities", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updateEntity(Integer id, Businessentity be) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Businessentity> entity = new HttpEntity<Businessentity>(be, headers);

		return template.exchange("http://localhost:8080/entities/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}
	
}
