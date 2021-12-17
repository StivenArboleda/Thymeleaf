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

import com.icesi.edu.Stiven.model.sales.Store;
import com.icesi.edu.Stiven.service.impl.StoreService;

@RequestMapping("/api")
@RestController
public class StoreRestController {

	@Autowired
	private StoreService ss;
	
	@GetMapping("/storeRest/list")
	public Iterable<Store> showStoreList() {
		return ss.findAll();
	}
	
	@PostMapping("/storeRest/add/")
	public void addStore(@RequestBody Store store) {
		ss.save(store);
	}
	
	@DeleteMapping("/storeRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ss.delete(ss.findbyId(id));
	}
	
	@GetMapping("/storeRest/find/{id}")
	public Store viewStore(@PathVariable("id") Integer id) {

		return ss.findbyId(id);
	}

	@PutMapping("/storeRest/edit/{id}")
	public void editStore(@RequestBody Store store) {

		ss.editStore(store);
	}
}
