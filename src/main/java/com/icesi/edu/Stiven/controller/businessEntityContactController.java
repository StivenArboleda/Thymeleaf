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
import com.icesi.edu.Stiven.model.person.Businessentitycontact;
import com.icesi.edu.Stiven.model.person.Contacttype;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.service.impl.ContactTypeService;
import com.icesi.edu.Stiven.service.inter.IAddressService;
import com.icesi.edu.Stiven.service.inter.IAddressTypeService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityAddressService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityContactService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityService;
import com.icesi.edu.Stiven.service.inter.IContactTypeService;
import com.icesi.edu.Stiven.service.inter.IPersonService;


@Controller
public class businessEntityContactController {

//	private IBusinessEntityContactService bea;
//	private IBusinessEntityService bes;
//	private IContactTypeService cts;
//	private IPersonService ps;
//
//	@Autowired
//	public businessEntityContactController(IBusinessEntityContactService bea, IBusinessEntityService bes,
//			IContactTypeService cts, IPersonService ps) {
//		super();
//		this.bea = bea;
//		this.bes = bes;
//		this.cts = cts;
//		this.ps = ps;
//	}
	
	private BusinessDelegate ps;
	
	@Autowired
	public businessEntityContactController(BusinessDelegate ps) {
		this.ps = ps;
	}
	
	@GetMapping("/businessentitycontact/")
	public String index(Model model) {
					
		model.addAttribute("businessentitycontacts", ps.showContactList());
		return "businessentitycontact/index";
	}
	
	@GetMapping("/businessentitycontact/searchAddress/{id}")
	public String search(Model model, @PathVariable Integer id) {
					
		model.addAttribute("businessentitycontact", ps.getFindByIdContact(id));
		return "businessentitycontact/searchAddress";
	}
	
	@GetMapping("/businessentitycontact/addContact")
	public String addressAdd(Model model) {
		
		Businessentitycontact bec = new Businessentitycontact();
		
		model.addAttribute("businessentitycontact", bec);
		model.addAttribute("businesses", ps.showBusinessList());
		model.addAttribute("contacts", ps.showContactTpList());
		model.addAttribute("persons", ps.showPersonList());
		
		return "businessentitycontact/addContact";
	}
	
	@PostMapping("/businessentitycontact/addContact")	
	public String addAddress(@ModelAttribute("businessentitycontact") Businessentitycontact beadress,
			@RequestParam(value="action", required=true) String action, Model model) {
		
		Businessentity be = beadress.getBusinessentity();
		Contacttype ct = beadress.getContacttype();
		Person p = beadress.getPerson();
		
		be = ps.saveEntity(be);
		ct = ps.addContactTp(ct);
		p = ps.addPerson(p);
		
		ps.addContact(beadress);
		
		return "redirect:/businessentitycontact/";
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
	
	@GetMapping("/businessentitycontact/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {

		ps.deleteContact(ps.getFindByIdContact(id));
		
		return "redirect:/businessentitycontact/";
	}
}
