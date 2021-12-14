package com.icesi.edu.Stiven.controller;

import org.springframework.http.HttpHeaders;
import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.icesi.edu.Stiven.model.person.Person;

@Component
public class DelegatePerson {
	
	
	private RestTemplate template;
	
	
	public Person getPerson(Integer id) {
		String url = "http://localhost:8080/persons/" + id;
		Person pe = template.getForObject(url, Person.class);
		return pe;
	}

	public Iterable<Person> getAllPersons() {
		String url = "http://localhost:8080/persons";
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public Iterable<Person> getAllPersonsByID(Integer id) {
		String url = "http://localhost:8080/persons/filter=" + id;
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public Iterable<Person> getAllPersonsByTitle(String title) {
		String url = "http://localhost:8080/persons/title=" + title;
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public String createPerson(Person pe) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(pe, headers);

		return template.exchange("http://localhost:8080/persons", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updatePerson(Integer id, Person pe) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(pe, headers);

		return template.exchange("http://localhost:8080/persons/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
}
