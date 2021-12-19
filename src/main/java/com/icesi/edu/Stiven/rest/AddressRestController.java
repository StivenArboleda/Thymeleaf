package com.icesi.edu.Stiven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.DAO.AddressDAO;
import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.service.impl.AddressService;

@RequestMapping("/api")
@RestController
public class AddressRestController {
	
	@Autowired
	private AddressService addres;
	
	//@Autowired
	//private AddressDAO addres;
	
	@GetMapping("/addressRest/")
	public Iterable<Address> showAddressList() {
		return addres.findAll();
	}
	
	@PostMapping("/addressRest/")
	public void addAddress(@RequestBody Address address) {
		addres.save(address);
	}
	
	@DeleteMapping("/addressRest/{id}")
	public void delete(@PathVariable("id") Integer id) {
		addres.deletebyId(id);
		//addres.delete(id);
	}
	
	@GetMapping("/addressRest/{id}")
	public Address viewAddress(@PathVariable("id") Integer id) {

		//return addres.findbyId(id);
		return addres.findById(id);
	}
	
	@PutMapping("/addressRest/{id}")
	public void editAddress(@RequestBody Address ad) {
		addres.editAddress(ad.getAddressid(), ad.getAddressline1(), ad.getAddressline2(),
			ad.getCity(), ad.getPostalcode(), ad.getStateprovince());
		//addres.update(ad);
	}
}
