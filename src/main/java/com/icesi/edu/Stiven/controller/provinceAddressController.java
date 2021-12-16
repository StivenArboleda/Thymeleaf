package com.icesi.edu.Stiven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.service.inter.IAddressService;
import com.icesi.edu.Stiven.service.inter.IStateProvinceService;


@Controller
public class provinceAddressController {

	//private IStateProvinceService ps;
	//private IAddressService as;
	private BusinessDelegate ps;
	
	/*/@Autowired
	public provinceAddressController(IStateProvinceService ps, IAddressService as) {
		this.ps = ps;
		this.as = as;
	}*/
	@Autowired
	public provinceAddressController(BusinessDelegate ps) {
		this.ps = ps;
	}
	
	
	@GetMapping("/provinceAddress/")
	public String index(Model model) {
		model.addAttribute("addresses", ps.showAddressList());
		return "/provinceAddress/index";
	}

	@GetMapping("/searchAddress/{id}")
	public String search(Model model, @PathVariable Integer id) {
		
		//model.addAttribute("address", as.findbyId(id));
		model.addAttribute("address", ps.getFindByIdAddress(id));
		return "provinceAddress/searchAddress";
	}
	
	@GetMapping("/provinceAddress/addAddress")
	public String addressAdd(Model model) {
		
		Address address = new Address();
		
		model.addAttribute("address", address);
		model.addAttribute("stateprovinces", ps.showProvinceList());
		
		return "provinceAddress/addAddress";
	}
	
	@PostMapping("/provinceAddress/addAddress")	
	public String addAddress(@ModelAttribute("address") Address address,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		//model.addAttribute("stateprovinces", ps.findAll());
		model.addAttribute("stateprovinces", ps.showProvinceList());
		Stateprovince sp = new Stateprovince();
		sp = address.getStateprovince();
		//sp = ps.saveCorrect(sp);
		//sp = ps.addProvince(sp);
		//address.setStateprovince(sp);
		//as.save(address);
		ps.addAddress(address);
		
		return "redirect:/provinceAddress/";
	}
	
	@GetMapping("/updateAddress/{id}")
	public String addresUpdate(Model model, @PathVariable Integer id) {
		
		//model.addAttribute("address", as.findbyId(id));
		//model.addAttribute("stateprovinces", ps.findAll());
		model.addAttribute("address", ps.getFindByIdAddress(id));
		model.addAttribute("stateprovinces", ps.showProvinceList());
		
		return "provinceAddress/updateAddress";
	}
	
	@PostMapping("/provinceAddress/updateAddress/{id}")
	public String updateAddress(Model model, @PathVariable Integer id, @ModelAttribute Address address,
			@RequestParam(value="action", required=true) String action) {
		
		//as.editAddress(id, address.getAddressline1(), address.getAddressline2(), address.getCity(),
			//	address.getPostalcode(), address.getStateprovince());
		
		ps.editAddress(id, address);
		return "redirect:/provinceAddress/";
	}
	
	
	@GetMapping("/provinceAddress/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {

		//as.deletebyId(id);
		Address a = ps.getFindByIdAddress(id);
		ps.deleteAddress(a);
		
		return "redirect:/provinceAddress/";
	}
}
