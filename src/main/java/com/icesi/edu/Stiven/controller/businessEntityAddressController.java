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
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.service.inter.IAddressService;
import com.icesi.edu.Stiven.service.inter.IAddressTypeService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityAddressService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityService;


@Controller
public class businessEntityAddressController {

	/*private IBusinessEntityAddressService bea;
	private IAddressService as;
	private IAddressTypeService ats;
	private IBusinessEntityService bes;*/
	

	/*@Autowired
	public businessEntityAddressController(IBusinessEntityAddressService bea, IAddressService as,
			IAddressTypeService ats, IBusinessEntityService bes) {
		this.bea = bea;
		this.as = as;
		this.ats = ats;
		this.bes = bes;
	}*/
	
	private BusinessDelegate bea;
	
	@Autowired
	public businessEntityAddressController(BusinessDelegate ps) {
		this.bea = ps;
	}
	
	
	@GetMapping("/businessAddress/")
	public String index(Model model) {
					
		model.addAttribute("businessentityaddresses", bea.showBusinessList());
		return "businessAddress/index";
	}
	
	@GetMapping("/businessAddress/searchAddress/{id}")
	public String search(Model model, @PathVariable Integer id) {
					
		model.addAttribute("businessentityaddress", bea.getFindByIdBusiness(id));
		return "businessAddress/searchAddress";
	}
	
	@GetMapping("/businessAddress/addAddress")
	public String addressAdd(Model model) {
		
		Businessentityaddress bea = new Businessentityaddress();
		
		/*model.addAttribute("businessentityaddress", bea);
		model.addAttribute("addresses", as.findAll());
		model.addAttribute("addresstypes", ats.findAll());
		model.addAttribute("businesses", bes.findAll());
		*/
		return "businessAddress/addAddress";
	}
	
	@PostMapping("/businessAddress/addAddress")	
	public String addAddress(@ModelAttribute("businessentityaddress") Businessentityaddress beadress,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		Address a = beadress.getAddress();
		Addresstype at = beadress.getAddresstype();
		Businessentity be = beadress.getBusinessentity();
		
		/*a = as.save(a);
		at = ats.save(at);
		be = bes.saveForAddress(be);
		
		bea.save(beadress, a.getAddressid(), at.getAddresstypeid(), be.getBusinessentityid());
		*/
		return "redirect:/businessAddress/";
	}
	
	@GetMapping("/businessAddress/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {
		
		Businessentityaddress bead = bea.getFindByIdBusiness(id);
		bea.deleteBusiness(bead);
		
		return "redirect:/businessAddress/";
	}
}
