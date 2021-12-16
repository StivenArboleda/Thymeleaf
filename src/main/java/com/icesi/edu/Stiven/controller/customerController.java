package com.icesi.edu.Stiven.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.model.sales.Store;
import com.icesi.edu.Stiven.service.inter.ICustomerService;
import com.icesi.edu.Stiven.service.inter.IPersonService;
import com.icesi.edu.Stiven.service.inter.IStoreService;

@Controller
public class customerController {
	private ICustomerService cs;
	private IStoreService ss;
	private IPersonService ps;
	
	@Autowired
	public customerController(ICustomerService cs, IStoreService ss, IPersonService ps) {
		this.cs = cs;
		this.ss = ss;
		this.ps = ps;
	}

	@GetMapping("/customers/")
	public String index(Model model) {
					
		model.addAttribute("customers", cs.findAll());
		return "customers/index";
	}
	
	@GetMapping("/customers/searchCustomer/{id}")
	public String search(Model model, @PathVariable Integer id) {
					
		model.addAttribute("customer", cs.findbyId(id));
		return "customers/searchCustomer";
	}
	
	@GetMapping("/customers/addCustomers")
	public String customerAdd(Model model) {
		
		Customer c = new Customer();
		
		model.addAttribute("customer", c);
		model.addAttribute("persons", ps.findAll());
		model.addAttribute("stores", ss.findAll());
		
		return "customers/addCustomer";
	}
	
	@PostMapping("/customers/addCustomer")	
	public String addCustomer(@ModelAttribute("customer") Customer customer,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		Store s = ss.findbyId(customer.getStoreid1());
		s.addCustomer(customer);
		customer.setStore(s);
		
		ss.editStore(s);
		cs.save(customer);
		
		return "redirect:/customers/";
	}
	
	@PostMapping("/customers/updateCustomer/{id}")
	public String updateCustomer(Model model, @PathVariable Integer id, @ModelAttribute Customer customer,
			@RequestParam(value = "action", required = true) String action) {

		Store s = ss.findbyId(customer.getStoreid1());
		Person p = ps.findbyId(customer.getPersonid());
		s.addCustomer(customer);
		p.addCustomer(customer);
		customer.setStore(s);
		customer.setPerson(p);

		ps.editPerson(p.getBusinessentityid(), p.getEmailpromotion(), p.getFirstname(), p.getLastname(), LocalDate.now(), p.getTitle());
		ss.editStore(s);
		cs.editCustomer(customer);
		
		return "redirect:/customers/";
	}

	@GetMapping("/updateCustomer/{id}")
	public String customerUpdate(Model model, @PathVariable Integer id) {
		
		model.addAttribute("customer", cs.findbyId(id));
		model.addAttribute("persons", ps.findAll());
		model.addAttribute("stores", ss.findAll());

		return "customers/updateCustomer";
	}
	
	@GetMapping("/customers/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {

		cs.deletebyId(id);
		
		return "redirect:/customers/";
	}
}
