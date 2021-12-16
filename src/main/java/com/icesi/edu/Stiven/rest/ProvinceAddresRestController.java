package com.icesi.edu.Stiven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.service.impl.StateProvinceService;

@RequestMapping("/api")
@RestController
public class ProvinceAddresRestController {
	
	@Autowired
	private StateProvinceService state;
	
	@GetMapping("/provinceRest/list")
	public Iterable<Stateprovince> showStateprovinceList() {
		return state.findAll();
	}
	
	@PostMapping("/provinceRest/addProvince/")
	public void addStateprovince(@RequestBody Stateprovince stateprovince) {
		state.saveCorrect(stateprovince);
	}
	
	@DeleteMapping("/provinceRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		state.delete(id);
	}
	
	@GetMapping("/provinceRest/find/{id}")
	public Stateprovince viewbusinessentityStateprovince(@PathVariable("id") Integer id) {

		return state.findbyId(id);
	}
}
