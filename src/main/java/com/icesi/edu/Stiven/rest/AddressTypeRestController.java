package com.icesi.edu.Stiven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.service.impl.AddressTypeService;

@RequestMapping("/api")
@RestController
public class AddressTypeRestController {

	@Autowired
	private AddressTypeService ads;
	
	@GetMapping("/atypeRest/list")
	public Iterable<Addresstype> showAddresstypeList() {
		return ads.findAll();
	}
	
	@PostMapping("/atypeRest/add/")
	public void addAddresstype(@RequestBody Addresstype atype) {
		ads.save(atype);
	}
	
	@DeleteMapping("/atypeRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ads.deletebyId(id);
	}
	
	@GetMapping("/atypeRest/find/{id}")
	public Addresstype viewAddresstype(@PathVariable("id") Integer id) {

		return ads.findbyId(id);
	}
}
