package com.icesi.edu.Stiven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.edu.Stiven.model.sales.Store;
import com.icesi.edu.Stiven.service.impl.StoreService;

@Controller
public class storeController {
	private StoreService ss;
	
	@Autowired
	public storeController(StoreService ss) {
		this.ss = ss;
	}
	
	@GetMapping("/stores/")
	public String index(Model model) {
					
		model.addAttribute("stores", ss.findAll());
		return "stores/index";
	}
	
	@GetMapping("/stores/searchStore/{id}")
	public String search(Model model, @PathVariable Integer id) {
					
		model.addAttribute("store", ss.findbyId(id));
		return "stores/searchStore";
	}
	
	@GetMapping("/stores/addStores")
	public String storeAdd(Model model) {
		
		Store s = new Store();
		
		model.addAttribute("store", s);
		
		return "stores/addStore";
	}
	
	@PostMapping("/stores/addStore")	
	public String addStore(@ModelAttribute("store") Store store,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		ss.save(store);
		
		return "redirect:/stores/";
	}
	
	@PostMapping("/stores/updateStore/{id}")
	public String updateStore(Model model, @PathVariable Integer id, @ModelAttribute Store store,
			@RequestParam(value = "action", required = true) String action) {

		ss.editStore(store);

		return "redirect:/stores/";
	}

	@GetMapping("/updateStore/{id}")
	public String storeUpdate(Model model, @PathVariable Integer id) {
		
		model.addAttribute("store", ss.findbyId(id));

		return "stores/updateStore";
	}
	
	@GetMapping("/stores/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {

		ss.deletebyId(id);
		
		return "redirect:/stores/";
	}
}
