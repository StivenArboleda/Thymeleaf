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

import com.icesi.edu.Stiven.DAO.BusinessEntityAddressDAO;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.service.impl.BusinessEntityAddressService;

@RequestMapping("/api")
@RestController
public class BusinessentityaddressRestController {
	
	@Autowired
	private BusinessEntityAddressService businessService;
	
	//@Autowired
	//private BusinessEntityAddressDAO businessService;
	
	@GetMapping("/businessentityRest/")
	public Iterable<Businessentityaddress> showbusinessentityaddressList() {
		return businessService.findAll();
	}
	
	@PostMapping("/businessentityRest/")
	public void addbusinessentityaddress(@RequestBody Businessentityaddress businessentityaddress) {
		businessService.save(businessentityaddress, businessentityaddress.getAddress().getAddressid(),
			businessentityaddress.getAddresstype().getAddresstypeid(), 
			businessentityaddress.getBusinessentity().getBusinessentityid());
		//businessService.save(businessentityaddress);
	}
	
	@DeleteMapping("/businessentityRest/{id}")
	public void delete(@PathVariable("id") Integer id) {
		businessService.deletebyId(id);
		//businessService.delete(id);
	}
	
	@GetMapping("/businessentityRest/{id}")
	public Businessentityaddress viewbusinessentityaddress(@PathVariable("id") Integer id) {

		return businessService.findById(id);
	}
}
	
