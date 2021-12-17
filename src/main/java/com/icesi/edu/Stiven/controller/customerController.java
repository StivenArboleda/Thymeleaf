package com.icesi.edu.Stiven.controller;

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

@Controller
public class customerController {
//	private ICustomerService cs;
//	private IStoreService ss;
//	private IPersonService ps;
//	
//	@Autowired
//	public customerController(ICustomerService cs, IStoreService ss, IPersonService ps) {
//		this.cs = cs;
//		this.ss = ss;
//		this.ps = ps;
//	}
	
	private BusinessDelegate bd;
	
	@Autowired
	public customerController(BusinessDelegate bd) {
		this.bd = bd;
	}

	@GetMapping("/customers/")
	public String index(Model model) {
					
		model.addAttribute("customers", bd.showCustomerList());
		return "customers/index";
	}
	
	@GetMapping("/customers/searchCustomer/{id}")
	public String search(Model model, @PathVariable Integer id) {
					
		model.addAttribute("customer", bd.getFindByIdCustomer(id));
		return "customers/searchCustomer";
	}
	
	@GetMapping("/customers/addCustomers")
	public String customerAdd(Model model) {
		
		Customer c = new Customer();
		
		model.addAttribute("customer", c);
		model.addAttribute("persons", bd.showPersonList());
		model.addAttribute("stores", bd.showStoreList());
		
		return "customers/addCustomer";
	}
	
	@PostMapping("/customers/addCustomer")	
	public String addCustomer(@ModelAttribute("customer") Customer customer,
			@RequestParam(value="action", required=true) String action, Model model) {
		System.out.println(customer.getStoreid1());
		Store s = bd.getFindByIdStore(customer.getStoreid1());
		Person p = bd.getFindByIdPerson(customer.getPersonid());
		s.addCustomer(customer);
		p.addCustomer(customer);
		customer.setStore(s);
		customer.setPerson(p);
		
		System.out.println(customer.getStore().getBusinessentityid());
		
		bd.editPerson(p.getBusinessentityid(), p);
		bd.editStore(s.getBusinessentityid(), s);
		bd.addCustomer(customer);
		
		return "redirect:/customers/";
	}
	
	@PostMapping("/customers/updateCustomer/{id}")
	public String updateCustomer(Model model, @PathVariable Integer id, @ModelAttribute Customer customer,
			@RequestParam(value = "action", required = true) String action) {

		Store s = bd.getFindByIdStore(customer.getStoreid1());
		Person p = bd.getFindByIdPerson(customer.getPersonid());
		s.addCustomer(customer);
		p.addCustomer(customer);
		customer.setStore(s);
		customer.setPerson(p);

		bd.editPerson(p.getBusinessentityid(), p);
		bd.editStore(s.getBusinessentityid(), s);
		bd.editCustomer(customer.getCustomerid(), customer);
		
		return "redirect:/customers/";
	}

	@GetMapping("/updateCustomer/{id}")
	public String customerUpdate(Model model, @PathVariable Integer id) {
		
		model.addAttribute("customer", bd.getFindByIdCustomer(id));
		model.addAttribute("persons", bd.showPersonList());
		model.addAttribute("stores", bd.showStoreList());

		return "customers/updateCustomer";
	}
	
	@GetMapping("/customers/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {

		bd.deleteCustomer(bd.getFindByIdCustomer(id));
		
		return "redirect:/customers/";
	}
}
