package com.icesi.edu.Stiven.service.impl;


import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.AddressTypeDAO;
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.service.inter.IAddressTypeService;


@Service
public class AddressTypeService implements IAddressTypeService{
	
	private AddressTypeDAO as;
	
	public AddressTypeService(AddressTypeDAO as) {
		this.as = as;
	}
	
	public <S extends Addresstype> S save(S address) {

		as.save(address);
				
		return address;
	}
	
	public <S extends Addresstype> Iterable<S> saveAll(Iterable<S> ads) {
		for (Addresstype addre : ads) {
			save(addre);
		}
		return ads;
	}
	
	public Addresstype findById(Integer id) {
		return as.findById(id);
	}
	

	@Override
	public Iterable<Addresstype> findAll() {
		return as.findAll();
	}
	
	@Override
	public Addresstype findbyId(Integer id) {
		return as.findById(id);
	}

	@Override
	public void deletebyId(Integer id) {
		as.deleteId(id);
	}


	/*public void editAddress(Integer addressid) {
		
		if(!(city.isEmpty()) && !(postalcode.isEmpty()) && !(addressline1.isEmpty())) {
			
						
			Address ads = as.findById(addressid).get();
			
			ads.setAddressline1(addressline1);
			ads.setAddressline2(addressline2);
			ads.setCity(city);
			ads.setPostalcode(postalcode);
			
			sp = sps.saveCorrect(sp);
			ads.setStateprovince(sp);
			
			save(ads);
			
		}else {
			throw new IllegalArgumentException("One of the arguments is invalid");
		}
		
	}*/
}
