package com.icesi.edu.Stiven.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityAddressService;


@Controller
public class businessEntityAddressController {

	private IBusinessEntityAddressService bea;
	
	@Autowired
	public businessEntityAddressController(IBusinessEntityAddressService bea) {
		this.bea = bea;
	}
	
	@GetMapping("/businessAddress/")
	public String index(Model model) {
		model.addAttribute("businessentityaddresses", bea.findAll());

		return "/businessAddress/index";
	}
	
	@GetMapping("/businessAddress/addAddress")
	public String addressAdd(Model model) {
		
		Businessentityaddress bea = new Businessentityaddress();
		model.addAttribute("businessentityaddress", bea);
				
		return "businessAddress/addAddress";
	}
	
	@PostMapping("/businessAddress/addAddress")	
	public String addAddress(@ModelAttribute("address") Businessentityaddress beadress,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		bea.save(beadress);
		
		return "redirect:/businessAddress/";
	}
	
	/*@GetMapping("/updateAddress/{id}")
	public String addresUpdate(Model model, @PathVariable Integer id) {
		
		model.addAttribute("address", as.findbyId(id));
		model.addAttribute("stateprovinces", ps.findAll());
		
		return "provinceAddress/updateAddress";
	}
	
	@PostMapping("/provinceAddress/updateAddress/{id}")
	public String updateAddress(Model model, @PathVariable Integer id, @ModelAttribute Address address,
			@RequestParam(value="action", required=true) String action) {
		
		as.editAddress(id, address.getAddressline1(), address.getAddressline2(), address.getCity(),
				address.getPostalcode(), address.getStateprovince());
		
		return "redirect:/provinceAddress/";
	}*/
	
	
	@GetMapping("/businessAddress/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {

		bea.deletebyId(id);
		
		return "redirect:/businessAddress/";
	}
}
