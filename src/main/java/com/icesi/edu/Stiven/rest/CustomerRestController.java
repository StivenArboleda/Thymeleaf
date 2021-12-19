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

import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.service.impl.CustomerService;

@RequestMapping("/api")
@RestController
public class CustomerRestController {
	
	@Autowired
	private CustomerService cs;
	
	@GetMapping("/customerRest/")
	public Iterable<Customer> showCustomerList() {
		return cs.findAll();
	}
	
	@PostMapping("/customerRest/")
	public void addCustomer(@RequestBody Customer customer) {
		cs.save(customer);
	}
	
	@DeleteMapping("/customerRest/{id}")
	public void delete(@PathVariable("id") Integer id) {
		cs.delete(cs.findbyId(id));
	}
	
	@GetMapping("/customerRest/{id}")
	public Customer viewCustomer(@PathVariable("id") Integer id) {

		return cs.findbyId(id);
	}

	@PutMapping("/customerRest/{id}")
	public void editCustomer(@RequestBody Customer customer) {

		cs.editCustomer(customer);
	}
}
