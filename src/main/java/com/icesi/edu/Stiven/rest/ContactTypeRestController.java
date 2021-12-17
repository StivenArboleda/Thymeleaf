package com.icesi.edu.Stiven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.model.person.Contacttype;
import com.icesi.edu.Stiven.service.impl.ContactTypeService;

@RequestMapping("/api")
@RestController
public class ContactTypeRestController {
	
	@Autowired
	private ContactTypeService cs;
	
	@GetMapping("/contactTpRest/list")
	public Iterable<Contacttype> showContactTpList() {
		return cs.findAll();
	}
	
	@PostMapping("/contactTpRest/add/")
	public void addContactTp(@RequestBody Contacttype contactTp) {
		cs.save(contactTp);
	}
	
	@DeleteMapping("/contactTpRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		cs.deletebyId(id);
	}
	
	@GetMapping("/contactTpRest/find/{id}")
	public Contacttype viewContactTp(@PathVariable("id") Integer id) {

		return cs.findbyId(id);
	}
}
